package sample.hello.resources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.verizon.bs.utils.Database;

@Path("/getBill")
public class HelloResource {
	@GET
	@Path("/bysmid/{i}")
	@Produces(MediaType.TEXT_XML)
	public String sayHello(@PathParam("i") String i) 
	{
		
		System.out.println("input: " + i);
		Connection conn = null;
	    PreparedStatement ps_select = null;
	    ResultSet rs = null; 
	    String Response = null;
	    try {
			conn = Database.getConnection();
	    	String billQuery = "select b.name, b.phone, c.sm_id, c.currency_type, c.start_ts, start_reading, end_ts, "
	        			+ "end_reading, total_units, calculated_amount, tax, bill_amount, currency_type, invoice_id from customer_invoice c, customer b where c.sm_id=? and b.id=c.cust_id order by c.invoice_id desc";
	    	ps_select = conn.prepareStatement(billQuery);
	    	ps_select.setString(1, i);   
	    	rs = ps_select.executeQuery();
	    	
	    	if (rs.next())
	    	{
	    		Response = "<INVOICE>"
	    		+ "<CUST_NAME>"+rs.getString(1)+"</CUST_NAME>"
	    		+ "<PHONE>"+rs.getString(2)+"</PHONE>"
	    		+ "<SM_ID>"+rs.getString(3)+"</SM_ID>"
	    		+ "<START_DATE>"+rs.getString(5)+"</START_DATE>"
	    		+ "<END_DATE>"+rs.getString(7)+"</END_DATE>"
	    		+ "<MIN_METER_READING>"+rs.getString(6)+"</MIN_METER_READING>"
	    	    + "<MAX_METER_READING>"+rs.getString(8)+"</MAX_METER_READING>"
	    	    + "<TOTAL_UNITS>"+rs.getString(9)+"</TOTAL_UNITS>"
	    	    + "<BILL_AMOUNT>"+rs.getString(10)+"</BILL_AMOUNT>"
	    	    + "<TAX>"+rs.getString(11)+"</TAX>"
	    	    + "<TOTAL_AMOUNT>"+rs.getString(12)+"</TOTAL_AMOUNT>"
	    	    + "<CURRENCY_TYPE>"+rs.getString(13)+"</CURRENCY_TYPE>"
	    	    + "</INVOICE>";
	    	}
	    }
	    catch(Exception e){
        e.printStackTrace();
	    }
	    finally {
	    	try {
	    		if (rs != null)
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
		System.out.println("Response: " + Response);
		return Response;
	}
}
