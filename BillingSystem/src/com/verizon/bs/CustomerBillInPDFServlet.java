package com.verizon.bs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.verizon.bs.forms.BillInformation;
import com.verizon.bs.utils.Database;
 
public class CustomerBillInPDFServlet extends HttpServlet{
 
    private static final long serialVersionUID = 1L;
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        System.out.println("----- CustomerBillInPDFServlet -----");
        Connection conn = null;
        PreparedStatement ps_select = null;
    	ResultSet rs = null;    	
        try {
        	String invoiceId=request.getParameter("invoiceid");
        	conn = Database.getConnection();
        	String billQuery = "select b.name, b.phone, c.sm_id, c.currency_type, c.start_ts, start_reading, end_ts, "
            			+ "end_reading, total_units, calculated_amount, tax, bill_amount, currency_type, invoice_id from customer_invoice c, customer b where c.invoice_id=? and b.id=c.cust_id";
        	ps_select = conn.prepareStatement(billQuery);
        	ps_select.setString(1, invoiceId);   
        	rs = ps_select.executeQuery();
        	BillInformation bill =null;     
        	
        	ServletOutputStream os = response.getOutputStream(); 
        	response.setContentType("application/pdf");
        	Document doc = new Document();
        	
        	Font bfBold18 = new Font(Font.TIMES_ROMAN, 18, Font.BOLD, CMYKColor.BLACK);    
        	Font bfBold12 = new Font(Font.TIMES_ROMAN, 12, Font.BOLD, CMYKColor.BLACK);  
        	Font bf12 = new Font(Font.TIMES_ROMAN, 12);  
        	PdfWriter.getInstance(doc, os); 
        	doc.open();        	
        	
        	Paragraph preface1 = new Paragraph();
        	preface1.setSpacingAfter(10);
        	preface1.setAlignment(Rectangle.ALIGN_CENTER);
        	preface1.setFont(bfBold18);
        	preface1.add(new Phrase("Bill Details"));
        	doc.add(preface1);
        	
        	PdfPTable maintable = new PdfPTable(2);
        	float[] columnwidths = new float[] {30f, 40f};
        	maintable.setWidths(columnwidths);
        	maintable.setWidthPercentage(70);
        	maintable.setHorizontalAlignment(Element.ALIGN_CENTER);
        	
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
	    		
	    		PdfPCell cell = new PdfPCell(new Phrase("Customer Name", bfBold12));
	        	cell.setVerticalAlignment(Element.ALIGN_CENTER);
	        	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        	cell.setFixedHeight(25);
	        	maintable.addCell(cell);
	        	
	        	cell = new PdfPCell(new Phrase(bill.getCust_name(), bf12));
	        	cell.setVerticalAlignment(Element.ALIGN_CENTER);
	        	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        	maintable.addCell(cell);
	        	
	        	cell = new PdfPCell(new Phrase("Phone Number", bfBold12));
	        	cell.setVerticalAlignment(Element.ALIGN_CENTER);
	        	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        	cell.setFixedHeight(25);
	        	maintable.addCell(cell);
	        	
	        	cell = new PdfPCell(new Phrase(bill.getPhone_num(), bf12));
	        	cell.setVerticalAlignment(Element.ALIGN_CENTER);
	        	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        	maintable.addCell(cell);
	        	
	        	cell = new PdfPCell(new Phrase("Smart Meter ID", bfBold12));
	        	cell.setVerticalAlignment(Element.ALIGN_CENTER);
	        	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        	cell.setFixedHeight(25);
	        	maintable.addCell(cell);
	        	
	        	cell = new PdfPCell(new Phrase(bill.getMeter_id(), bf12));
	        	cell.setVerticalAlignment(Element.ALIGN_CENTER);
	        	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        	maintable.addCell(cell);
	        	
	        	cell = new PdfPCell(new Phrase("Start Date", bfBold12));
	        	cell.setVerticalAlignment(Element.ALIGN_CENTER);
	        	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        	cell.setFixedHeight(25);
	        	maintable.addCell(cell);
	        	
	        	cell = new PdfPCell(new Phrase(bill.getStart_date(), bf12));
	        	cell.setVerticalAlignment(Element.ALIGN_CENTER);
	        	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        	maintable.addCell(cell);
	        	
	        	cell = new PdfPCell(new Phrase("End Date", bfBold12));
	        	cell.setVerticalAlignment(Element.ALIGN_CENTER);
	        	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        	cell.setFixedHeight(25);
	        	maintable.addCell(cell);
	        	
	        	cell = new PdfPCell(new Phrase(bill.getEnd_date(), bf12));
	        	cell.setVerticalAlignment(Element.ALIGN_CENTER);
	        	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        	maintable.addCell(cell);
	        	
	        	cell = new PdfPCell(new Phrase("Start Meter Reading", bfBold12));
	        	cell.setVerticalAlignment(Element.ALIGN_CENTER);
	        	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        	cell.setFixedHeight(25);
	        	maintable.addCell(cell);
	        	
	        	cell = new PdfPCell(new Phrase(bill.getMinMeterReading(), bf12));
	        	cell.setVerticalAlignment(Element.ALIGN_CENTER);
	        	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        	maintable.addCell(cell);
	        	
	        	cell = new PdfPCell(new Phrase("End Meter Reading", bfBold12));
	        	cell.setVerticalAlignment(Element.ALIGN_CENTER);
	        	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        	cell.setFixedHeight(25);
	        	maintable.addCell(cell);
	        	
	        	cell = new PdfPCell(new Phrase(bill.getMaxMeterReading(), bf12));
	        	cell.setVerticalAlignment(Element.ALIGN_CENTER);
	        	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        	maintable.addCell(cell);
	        	
	        	cell = new PdfPCell(new Phrase("Current Meter Reading", bfBold12));
	        	cell.setVerticalAlignment(Element.ALIGN_CENTER);
	        	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        	cell.setFixedHeight(25);
	        	maintable.addCell(cell);
	        	
	        	cell = new PdfPCell(new Phrase(bill.getTotal_units(), bf12));
	        	cell.setVerticalAlignment(Element.ALIGN_CENTER);
	        	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        	maintable.addCell(cell);
	        	
	        	cell = new PdfPCell(new Phrase("Rate/Meter Reading", bfBold12));
	        	cell.setVerticalAlignment(Element.ALIGN_CENTER);
	        	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        	cell.setFixedHeight(25);
	        	maintable.addCell(cell);
	        	
	        	cell = new PdfPCell(new Phrase(bill.getRate(), bf12));
	        	cell.setVerticalAlignment(Element.ALIGN_CENTER);
	        	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        	maintable.addCell(cell);
	        	
	        	cell = new PdfPCell(new Phrase("Tax", bfBold12));
	        	cell.setVerticalAlignment(Element.ALIGN_CENTER);
	        	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        	cell.setFixedHeight(25);
	        	maintable.addCell(cell);
	        	
	        	cell = new PdfPCell(new Phrase(bill.getTax(), bf12));
	        	cell.setVerticalAlignment(Element.ALIGN_CENTER);
	        	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        	maintable.addCell(cell);
	        	
	        	cell = new PdfPCell(new Phrase("Billed Amount", bfBold12));
	        	cell.setVerticalAlignment(Element.ALIGN_CENTER);
	        	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        	cell.setFixedHeight(25);
	        	maintable.addCell(cell);
	        	
	        	cell = new PdfPCell(new Phrase(bill.getTotal()+" "+bill.getCurrency_type(), bf12));
	        	cell.setVerticalAlignment(Element.ALIGN_CENTER);
	        	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        	maintable.addCell(cell);
	        	
	        	doc.add(maintable);
	    		
        	}
        	     	
        	doc.close();  
        	
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
