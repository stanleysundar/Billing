package com.verizon.bs;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.verizon.bs.forms.ServiceProvider;
import com.verizon.bs.forms.SlabDetails;
import com.verizon.bs.utils.Database;
 
public class ServiceProvideSlabData extends HttpServlet{
 
    private static final long serialVersionUID = 1L;
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        System.out.println("----- ServiceProvideSlabData -----");
        Connection conn = null;
        PreparedStatement ps_select = null;
    	ResultSet rs = null;
    	ArrayList<SlabDetails> slabList = new ArrayList<SlabDetails>();
    	try {
    		conn = Database.getConnection();
        	String serviceProviderQuery = "select s.start_reading,s.end_reading,s.rate,sp.provider_name from slab s, service_provider sp where s.provider_id=sp.provider_id order by s.provider_id, start_reading asc";
        	ps_select = conn.prepareStatement(serviceProviderQuery);
        	rs = ps_select.executeQuery();    
        	SlabDetails sb = null;
            while(rs.next())
        	{
            	sb=new SlabDetails();
            	sb.setStartReading(rs.getString(1));
            	sb.setEndReading(rs.getString(2));
            	sb.setRate(rs.getString(3));
            	sb.setProvider_name(rs.getString(4));
            	slabList.add(sb);    
        	}
            RequestDispatcher dispatcher=request.getRequestDispatcher("/viewSlabDetails.jsp");
            request.setAttribute("slabList",slabList);
            dispatcher.forward(request, response);
            
        } catch(Exception e){
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
