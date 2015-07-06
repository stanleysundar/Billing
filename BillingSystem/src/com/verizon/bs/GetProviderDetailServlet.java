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
import com.verizon.bs.forms.ServiceProvider;
import com.verizon.bs.utils.Database;
 
public class GetProviderDetailServlet extends HttpServlet{
 
    private static final long serialVersionUID = 1L;
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        System.out.println("----- GetProviderDetailServlet -----");
        Connection conn = null;
        PreparedStatement ps_select = null;
    	ResultSet rs = null;
        try {
        	String serviceProviderQuery = "select * from service_provider";
        	
        	conn = Database.getConnection();
        	ps_select = conn.prepareStatement(serviceProviderQuery);
        	rs = ps_select.executeQuery();
        	ArrayList<ServiceProvider> providerList = new ArrayList<ServiceProvider>();
            //Set the Customer values into Customer Bean or POJO(Plain Old Java Object) class
        	while(rs.next())
        	{
        		System.out.println("Name -- " + rs.getString(1));
        		ServiceProvider serviceProvider=new ServiceProvider();
        		serviceProvider.setProvider_id(rs.getString(1));
        		serviceProvider.setProvider_name(rs.getString(2)); 
        		providerList.add(serviceProvider);                
        	}
            RequestDispatcher dispatcher=request.getRequestDispatcher("/GenBillInitScreen.jsp");
            request.setAttribute("providerList",providerList);
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
         
    }   
 
}
