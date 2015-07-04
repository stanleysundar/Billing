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


import com.verizon.bs.forms.Customer;
import com.verizon.bs.utils.Database;
 
public class ListCustomer extends HttpServlet{
 
    private static final long serialVersionUID = 1L;
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        System.out.println("----- ListCustomerServlet -----");
        Connection conn = null;
        PreparedStatement ps_select = null;
    	ResultSet rs = null;
        try {
        // Get the customer value submitted from Customer.jsp page through HttpServletRequest object
        	boolean status = false;
        	
        	String saveQuery = "select * from customer";
        	
        	conn = Database.getConnection();
        	ps_select = conn.prepareStatement(saveQuery);
        	rs = ps_select.executeQuery();
        	ArrayList<Customer> custList = new ArrayList<Customer>();
            //Set the Customer values into Customer Bean or POJO(Plain Old Java Object) class
        	while(rs.next())
        	{
        		System.out.println("Name -- " + rs.getString(1));
        		Customer customer=new Customer();
                customer.setName(rs.getString(1));
                customer.setSm_id(rs.getString(2));
                customer.setAddress(rs.getString(3));
                customer.setMobile(Long.valueOf(rs.getString(5)));
                customer.setEmailid(rs.getString(4));
                custList.add(customer);                
        	}


            RequestDispatcher dispatcher=request.getRequestDispatcher("/ListCustomer.jsp");
            //Set the customer instance into request.Then only the customer object 
            //will be available in the Welcome.jsp page
            request.setAttribute("custList",custList);
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
