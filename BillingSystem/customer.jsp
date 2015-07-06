<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="mainstyle.css" rel="stylesheet" type="text/css" />
<title>Customer Details</title>
</head>
<%@include file="HeaderPage.jsp" %>
<table broder="0">
<tr><td>
<%@include file="SideMenu.jsp" %>
</td>

<td align="center" width="100%">
<br><br><br><br><br><br>

<form action="insertCustomer" method="post">
<table align="center" bgcolor="#ededed" border="1" width="50%">
    <tr>
        <td colspan="2" align="center" class="pageHeader">Customer Details </td>
    </tr>
    <tr>
        <td class="normalStyleHeader">Customer Name </td>
        <td style="padding: 5px 0 5px 5px;"><input type="text" name="name" maxlength="100" size="50"></td>
    </tr>
    <tr>
        <td class="normalStyleHeader">Address </td>
        <td style="padding: 5px 0 5px 5px;"><input type="text" name="address" maxlength="200" size="50"></td>
    </tr>
    <tr>
        <td class="normalStyleHeader">Mobile </td>
        <td style="padding: 5px 0 5px 5px;"><input type="text" name="mobile" maxlength="10" size="30"></td>
    </tr>
    <tr>
        <td class="normalStyleHeader">Email Id </td>
        <td style="padding: 5px 0 5px 5px;"><input type="text" name="emailid" maxlength="20" size="30"></td>
    </tr> 
    <tr>
        <td colspan="2" align="center" style="padding: 5px 0 5px 5px;"><input type="submit" value="Submit"></td>
    </tr> 
</table>
</form>
</td></tr></table>
</html>
