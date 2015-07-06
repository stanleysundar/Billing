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
import com.verizon.bs.forms.ServiceProvider;
import com.verizon.bs.utils.Database;
 
public class ListCustomer extends HttpServlet{
 
    private static final long serialVersionUID = 1L;
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        System.out.println("----- ListCustomerServlet -----");
        Connection conn = null;
        PreparedStatement ps_select = null;
    	ResultSet rs = null;
    	ArrayList<ServiceProvider> providerList = new ArrayList<ServiceProvider>();
    	ArrayList<BillInformation> billInfoList = new ArrayList<BillInformation>(); 
        try {
        	String providerId=request.getParameter("providerId");
        	String startDate=request.getParameter("strDate");
            String endDate=request.getParameter("endDate");
        	conn = Database.getConnection();
        	String serviceProviderQuery = "select * from service_provider";
        	ps_select = conn.prepareStatement(serviceProviderQuery);
        	rs = ps_select.executeQuery();            	
            //Set the Customer values into Customer Bean or POJO(Plain Old Java Object) class
        	while(rs.next())
        	{
        		System.out.println("Name -- " + rs.getString(1));
        		ServiceProvider serviceProvider=new ServiceProvider();
        		serviceProvider.setProvider_id(rs.getString(1));
        		serviceProvider.setProvider_name(rs.getString(2)); 
        		providerList.add(serviceProvider);                
        	}
            
            if(providerId != null && !providerId.equals("0"))
            {
            	String billQuery = "select b.name, b.phone, c.sm_id, c.currency_type, c.start_ts, start_reading, end_ts, "
            			+ "end_reading, total_units, calculated_amount, tax, bill_amount, currency_type, invoice_id from customer_invoice c, customer b where c.provider_id=? and c.cust_id=b.id"
            			+ " and DATE(start_ts) >= STR_TO_DATE(?,'%m/%d/%Y') and DATE(end_ts) <= STR_TO_DATE(?,'%m/%d/%Y') ";
            	ps_select = conn.prepareStatement(billQuery);
            	ps_select.setString(1, providerId);   
            	ps_select.setString(2, startDate);
            	ps_select.setString(3, endDate);   
            	rs = ps_select.executeQuery();
            	BillInformation bill =null;            	
            	while(rs.next())
            	{
            		bill = new BillInformation();
    	    		bill.setCust_name(rs.getString(1));
    	    		bill.setPhone_num(rs.getString(2));
    	    		bill.setMeter_id(rs.getString(3));
    	    		bill.setStart_date(rs.getString(5));
    	    		bill.setEnd_date(rs.getString(7));
    	    		bill.setMinMeterReading(rs.getString(6));
    	    		bill.setMaxMeterReading(rs.getString(8));
    	    		bill.setTotal_units(rs.getString(9));
    	    		bill.setRate(rs.getString(10));
    	    		bill.setTax(rs.getString(11));
    	    		bill.setTotal(rs.getString(12));
    	    		bill.setCurrency_type(rs.getString(13));
    	    		bill.setInvoice_id(rs.getString(14));
    	    		billInfoList.add(bill);
            	}
            }
        	
            RequestDispatcher dispatcher=request.getRequestDispatcher("/ViewGenBillScreen.jsp");
            request.setAttribute("providerList",providerList);
            request.setAttribute("billInfoList",billInfoList);
            //request.setAttribute("providerId",providerId);
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
        	try {
				rs.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		if(ps_select != null)
    		{
    			try {
    				ps_select.close();
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
    }   
   
}
