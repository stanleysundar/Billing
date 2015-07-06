<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.verizon.bs.forms.ServiceProvider"%> 
    <%@ page import ="java.util.*" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="mainstyle.css" rel="stylesheet" type="text/css" />
 <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
  <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
  <script>
$(function() {
    $( "#datepicker" ).datepicker();
});
$(function() {
    $( "#datepicker1" ).datepicker();
});
function goBack() {
     window.history.back();
 }
</script>
<title>Generate Bill Initial Screen</title>
</head>
<%@include file="HeaderPage.jsp" %>
<table broder="0">
<tr><td>
<%@include file="SideMenu.jsp" %>
</td>

<td align="center" width="100%">
<br><br><br><br><br><br>

<form action="generateBill" method="post">
<%
    ArrayList<ServiceProvider> providerList = (ArrayList<ServiceProvider>)request.getAttribute("providerList");
%>
<table align="center" bgcolor="#ededed" border="1" width="50%">
    <tr>
        <td colspan="2" align="center" class="pageHeader">Details For Bill Generation</td>
      
    </tr>
    <tr>
        <td class="normalStyleHeader">Provider Name</td>
        <td style="padding: 5px 0 5px 5px;">
<select name="providerId"> 
<option value="0" selected>(please select:)</option> 
<% for(ServiceProvider serviceProvider : providerList) {  %>
<option value=<%=serviceProvider.getProvider_id()%>><%=serviceProvider.getProvider_name()%></option>
<% } %> 
</select>
        </td>
    </tr>
    <tr>
        <td class="normalStyleHeader">Start Date: </td>
        <td style="padding: 5px 0 5px 5px;"><input name="strDate" type="text" id="datepicker" /></td>
    </tr>
    <tr>
        <td class="normalStyleHeader">End Date: </td>
        <td style="padding: 5px 0 5px 5px;"><input name="endDate" type="text" id="datepicker1" /></td>
    </tr>
    <tr>
        <td colspan="2" align="center" style="padding: 5px 0 5px 5px;"><input type="submit" value="Submit"></td>
    </tr> 
</table>
</form>
</td></tr></table>
</html>
