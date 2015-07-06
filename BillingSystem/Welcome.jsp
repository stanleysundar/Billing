<%@page import="com.verizon.bs.forms.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<link href="mainstyle.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Customer Details</title>
</head>
<%@include file="HeaderPage.jsp" %>
<table broder="0">
<tr><td>
<%@include file="SideMenu.jsp" %>
</td>

<td align="center" width="100%">
<br><br><br><br><br><br>

<%
    Customer customer=(Customer)request.getAttribute("cust");
%>
<table align="center" bgcolor="#ededed" border="1" width="50%">
    <tr>
        <td colspan="2" align="center" class="normalStyleHeader"><%="Welcome "+customer.getName()+"!!!!. Your details Processed." %></td>
    </tr>
    <tr >
        <td class="normalStyleHeader" width="30%">Customer Name </td>
        <td class="normalStyle"><%=customer.getName()%></td>
    </tr>
    <tr>
        <td class="normalStyleHeader">Address </td>
        <td class="normalStyle"><%=customer.getAddress() %></td>
    </tr>
    <tr>
        <td class="normalStyleHeader">Mobile </td>
        <td class="normalStyle"><%=String.valueOf(customer.getMobile()) %></td>
    </tr>
    <tr>
        <td class="normalStyleHeader">Email Id </td>
        <td class="normalStyle"><%=customer.getEmailid() %></td>
    </tr>
   
</table>

</td></tr></table>
</html>
