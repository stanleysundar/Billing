<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.verizon.bs.forms.Customer"%>
    <%@page import="com.verizon.bs.forms.ServiceProvider"%>  
    <%@ page import ="java.util.*" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="mainstyle.css" rel="stylesheet" type="text/css" />
<title>Add Customer and Service Provider Details</title>
</head>
<%@include file="HeaderPage.jsp" %>
<table broder="0">
<tr><td>
<%@include file="SideMenu.jsp" %>
</td>

<td align="center" width="100%">
<br><br><br><br><br><br>

<form action="insertCustProvider" method="post">
<%
    ArrayList<Customer> list = (ArrayList<Customer>)request.getAttribute("custList");
	ArrayList<ServiceProvider> providerList = (ArrayList<ServiceProvider>)request.getAttribute("providerList");
%>
<table align="center" bgcolor="#ededed" border="1" width="50%">
    <tr>
        <td colspan="2" align="center" class="pageHeader">Customer Meter Details </td>
    </tr>
    <tr>
        <td class="normalStyleHeader">Customer Name </td>
        <td style="padding: 5px 0 5px 5px;">
<select name="custId"> 
<option value="0" selected>(please select:)</option> 
<% for(Customer customer : list) {  %>
<option value=<%=customer.getId()%>><%=customer.getName()%></option>
<% } %> 
</select>
</td>
    </tr>
    <tr>
        <td class="normalStyleHeader">Provider Name </td>
        <td style="padding: 5px 0 5px 5px;">
<select name="providerId"> 
<option value="0" selected>(please select:)</option> 
<% for(ServiceProvider serviceProvider : providerList) {  %>
<option value=<%=serviceProvider.getProvider_id()%>><%=serviceProvider.getProvider_name()%></option>
<% } %> 
</select></td>
    </tr>
    <tr>
        <td class="normalStyleHeader">Smart Meter Id </td>
        <td style="padding: 5px 0 5px 5px;"><input type="text" name="smId" maxlength="10" size="10"></td>
    </tr>
    <tr>
        <td class="normalStyleHeader">Payment Currency Type </td>
        <td style="padding: 5px 0 5px 5px;">
<select name="currencyType"> 
<option value="INR" >INR</option> 
<option value="USD" >USD</option> 
</select></td>
    </tr> 
    <tr>
        <td colspan="2" align="center" style="padding: 5px 0 5px 5px;"><input type="submit" value="Submit"></td>
    </tr> 
</table>
</form>
</td></tr></table>
</html>
