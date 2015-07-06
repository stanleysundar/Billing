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
 
public class InsertCustProviderDetailServlet extends HttpServlet{
 
    private static final long serialVersionUID = 1L;
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        System.out.println("----- InsertCustProviderDetailServlet -----");
        try {
        // Get the customer value submitted from Customer.jsp page through HttpServletRequest object
            String custId=request.getParameter("custId");
            String providerId=request.getParameter("providerId");
            String smId=request.getParameter("smId");
            String currencyType=request.getParameter("currencyType");
       
            saveCustomer(custId, providerId, smId, currencyType);
            RequestDispatcher dispatcher=request.getRequestDispatcher("/CustProviderSuccess.jsp");
            //Set the customer instance into request.Then only the customer object 
            //will be available in the Welcome.jsp page
            request.setAttribute("smId",smId);
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
         
    }
    
    public boolean saveCustomer(String custId, String providerId, String smId, String currencyType)
    {
    	boolean status = false;
    	PreparedStatement ps_save = null;
    	String saveQuery = "insert into customer_service (cust_id,provider_id,sm_id,currency_type) values (?,?,?,?)";
    	Connection conn = null;
    	try
    	{
    		conn = Database.getConnection();
    		ps_save = conn.prepareStatement(saveQuery);
    		ps_save.setString(1, custId);    		
    		ps_save.setString(2, providerId);
    		ps_save.setString(3, smId);
    		ps_save.setString(4, currencyType);
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
