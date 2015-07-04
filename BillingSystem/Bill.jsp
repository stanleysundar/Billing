<%@page import="com.verizon.bs.forms.BillInformation"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
 function goBack() {
     window.history.back();
 }
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Bill</title>
</head>
<body>
<%
    BillInformation bill=(BillInformation)request.getAttribute("bill");
%>
<table align="center" bgcolor="#FFFFCC" border="1" width="70%">
    <tr>
        <td colspan="2" align="center"><%="Bill Details for period "+bill.getStart_date()+" to " + bill.getEnd_date()%></td>
    </tr>
    <tr>
        <td>Name </td>
        <td><%=bill.getCust_name()%></td>
    </tr>
    <tr>
        <td>Meter ID </td>
        <td><%=bill.getMeter_id() %></td>
    </tr>
    <tr>
        <td>Start Reading </td>
        <td><%=bill.getMinMeterReading() %></td>
    </tr>
    <tr>
        <td>End Reading </td>
        <td><%=bill.getMaxMeterReading() %></td>
    </tr>
     <tr>
        <td>Total Units </td>
        <td><%=bill.getTotal_units() %></td>
    </tr>
     <tr>
        <td>Amount Rs.</td>
        <td><%=bill.getRate() %></td>
    </tr>
    <tr>
        <td>Tax Rs.</td>
        <td><%=bill.getTax() %></td>
    </tr>
    <tr>
        <td>Final Bill Amount Rs.</td>
        <td><%=bill.getTotal() %></td>
    </tr>
   
</table>
<table align="center" width="70%">
	<tr align="center">
	<td><button onclick="goBack()">Go Back</button></td>
		
	</tr>
</table>
	

</body>
</html>
