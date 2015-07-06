<%@page import="com.verizon.bs.forms.Customer"%>
<%@ page import ="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
  <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
  <script>

<script>
$(function() {
    $( "#datepicker" ).datepicker();
});


 function goBack() {
     window.history.back();
 }
</script>



<title>View Customer Details</title>
</head>

<%
    ArrayList<Customer> list = (ArrayList<Customer>)request.getAttribute("custList");

%>
<table align="center" bgcolor="#FFFFCC" border="1" width="70%">
<tr>
	<td>Customer Namer</td>
	<td>Smart Meter ID</td>
	<td>Address</td>
	<td>Email</td>
	<td>Phone</td>	
	<td>Generate Bill</td>
</tr>
<% for(Customer customer : list) {  %>
	<tr>
		<td><%=customer.getName()%></td>
		<td><%=customer.getSm_id()%></td>
		<td><%=customer.getAddress() %></td>
		<td><%=customer.getEmailid() %></td>
		<td><%=String.valueOf(customer.getMobile()) %></td>		
		<td>
			<a href="selectDates.jsp?mid=<%=customer.getSm_id()%>&name=<%=customer.getName()%>">Select dates</a>
		</td>
		
	</tr>
<%}%>    
   
</table>
<table align="center" width="70%">
	<tr align="center">
	<td><button onclick="goBack()">Go Back</button></td>
		
	</tr>
</table>
	

</html>
