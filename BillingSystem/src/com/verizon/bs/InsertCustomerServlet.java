package com.verizon.bs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.verizon.bs.forms.Customer;
import com.verizon.bs.utils.Database;
 
public class InsertCustomerServlet extends HttpServlet{
 
    private static final long serialVersionUID = 1L;
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        System.out.println("----- InsertCustomerServlet -----");
        try {
        // Get the customer value submitted from Customer.jsp page through HttpServletRequest object
            String name=request.getParameter("name");
            String address=request.getParameter("address");
            String mobile=request.getParameter("mobile");
            String emailid=request.getParameter("emailid");
            String sm_id =  request.getParameter("sm_id");
            //Set the Customer values into Customer Bean or POJO(Plain Old Java Object) class
            Customer customer=new Customer();
            customer.setName(name);
            customer.setAddress(address);
            customer.setMobile(Long.valueOf(mobile));
            customer.setEmailid(emailid);
            customer.setSm_id(sm_id);
            saveCustomer(customer);
            RequestDispatcher dispatcher=request.getRequestDispatcher("/Welcome.jsp");
            //Set the customer instance into request.Then only the customer object 
            //will be available in the Welcome.jsp page
            request.setAttribute("cust",customer);
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
