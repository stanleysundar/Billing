<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<link href="mainstyle.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Customer and Service Provider Success</title>
</head>
<%@include file="HeaderPage.jsp" %>
<table broder="0">
<tr><td>
<%@include file="SideMenu.jsp" %>
</td>

<td align="center" width="100%">
<br><br><br><br><br><br>

<table align="center" border="0" width="50%">
    <tr>
        <td colspan="2" align="center" style="font: 25px arial, sans-serif; color: #777;"><%="Smart Meter Id "+request.getAttribute("smId")+" Successfully Added" %></td>
    </tr>    
</table>

</td></tr></table>
</html>
