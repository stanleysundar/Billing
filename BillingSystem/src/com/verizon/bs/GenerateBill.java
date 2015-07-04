package com.verizon.bs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.verizon.bs.forms.BillInformation;
import com.verizon.bs.forms.Customer;
import com.verizon.bs.utils.Database;
import com.verizon.bs.vo.ReadingObject;
 
public class GenerateBill extends HttpServlet{
 
    private static final long serialVersionUID = 1L;
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        System.out.println("----- InsertCustomerServlet -----");
        try {
        // Get the customer value submitted from Customer.jsp page through HttpServletRequest object
            String meterid=request.getParameter("mid");
            String startDate=request.getParameter("strDate");
            String endDate=request.getParameter("endDate");
            String name = request.getParameter("name");
            System.out.println("MID -- " + meterid);
            System.out.println("strDate -- " + startDate);
            System.out.println("endDate -- " + endDate);
            //Set the Customer values into Customer Bean or POJO(Plain Old Java Object) class
            
            Connection conn = null;
    		PreparedStatement pstmt = null,pstmt1=null;
    		ResultSet rs = null,rs1=null;
    		ReadingObject readingObj = null;
    		ArrayList readingDetails = new ArrayList();
    		String start_reading = null;
    		String end_reading = null;
    		
    		conn = Database.getConnection();
    		String query = "select start_reading,end_reading,rate from slab order by start_reading asc";
    	
    		pstmt = conn.prepareStatement(query);
    		
    		rs = pstmt.executeQuery();
    		
    		while(rs.next())
    		{
    			readingObj = new ReadingObject();
    			if (rs.getLong(1)!= 0)
    			{
    				readingObj.setMinMeterReading(rs.getLong(1)-1);
    			}
    			else
    			{
    				readingObj.setMinMeterReading(rs.getLong(1));
    			}
    			readingObj.setMaxMeterReading(rs.getLong(2));
    			System.out.println("rs.getLong(3):"+rs.getDouble(3));
    			readingObj.setRate(rs.getDouble(3));
    			
    			readingDetails.add(readingObj);
    		}
    		
    		String readingQuery = "select min(start_reading), max(end_reading), max(end_reading)-min(start_reading) as totalReading from sm_usage"
    				+ " where sm_id = ? and start_ts >= STR_TO_DATE(?,'%m/%d/%Y') and end_ts <= STR_TO_DATE(?,'%m/%d/%Y') ";
    		pstmt1 = conn.prepareStatement(readingQuery);
    		pstmt1.setString(1, meterid);
    		pstmt1.setString(2, startDate);
    		pstmt1.setString(3, endDate);
    		
    		
    		rs1 = pstmt1.executeQuery();
    		
    		long currentMeterReading = 0;
    		
    		if(rs1.next())
    		{
    			start_reading = rs1.getString(1);
    			end_reading = rs1.getString(2);
    			currentMeterReading = rs1.getLong(3);
    		}
    		System.out.println("start_reading: " + start_reading);
    		System.out.println("end_reading: " + end_reading);
    		System.out.println("currentMeterReading: " + currentMeterReading);
    		long minMeterReading;
    		long maxMeterReading;
    		long newMeterReading = currentMeterReading;
    		
    		double totalRate = 0;
    		double tax = 0;
    		double totalBillAmount = 0;
    		double rate = 0;
    		for(int i=0; i<readingDetails.size();i++)
    		{
    			if (newMeterReading > 0)
    			{
    				readingObj = (ReadingObject) readingDetails.get(i);
    				maxMeterReading = readingObj.getMaxMeterReading();
    				minMeterReading = readingObj.getMinMeterReading();
    				rate = readingObj.getRate();
    				System.out.println("rate:"+rate);
    				if (newMeterReading > maxMeterReading)
    				{
    					totalRate += (maxMeterReading - minMeterReading) * rate;
    					newMeterReading = currentMeterReading - maxMeterReading;					
    				}
    				else
    				{
    					totalRate += newMeterReading*rate;
    					newMeterReading=0;
    				}
    			}
    		}
    		System.out.println("Total rate =" +totalRate);
    		tax = totalRate * 0.1;
    		totalBillAmount = totalRate + tax;
    		
    		BillInformation bill = new BillInformation();
    		bill.setCust_name(name);
    		bill.setMeter_id(meterid);
    		bill.setStart_date(startDate);
    		bill.setEnd_date(endDate);
    		bill.setMinMeterReading(start_reading);
    		bill.setMaxMeterReading(end_reading);
    		bill.setTotal_units(currentMeterReading);
    		bill.setRate(totalRate);
    		bill.setTax(tax);
    		bill.setTotal(totalBillAmount);
            RequestDispatcher dispatcher=request.getRequestDispatcher("/Bill.jsp");
            //Set the customer instance into request.Then only the customer object 
            //will be available in the Welcome.jsp page
            request.setAttribute("bill",bill);
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
         
    }
    
    public boolean saveCustomer(Customer cust)
    {
    	boolean status = false;
    	PreparedStatement ps_save = null;
    	String saveQuery = "insert into customer values (?,?,?,?,?)";
    	Connection conn = null;
    	try
    	{
    		conn = Database.getConnection();
    		ps_save = conn.prepareStatement(saveQuery);
    		ps_save.setString(1, cust.getName());
    		ps_save.setString(2, cust.getSm_id());
    		ps_save.setString(3, cust.getAddress());
    		ps_save.setString(4, cust.getEmailid());
    		ps_save.setLong(5, cust.getMobile());
    		int count = ps_save.executeUpdate();
    		if(count > 0)
    		{
    			status = true;
    		}
    	}
    	catch(Exception sexp)
    	{
    		System.out.println("Exp while saving customer data...");
    		sexp.printStackTrace();
    	}
    	finally {
    		if(ps_save != null)
    		{
    			try {
					ps_save.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
			if(conn != null)
			{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    	return status;
    }
 
}
