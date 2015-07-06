<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.verizon.bs.forms.ServiceProvider"%> 
    <%@page import="com.verizon.bs.forms.BillInformation"%> 
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
 function showPDF(val)
 {
	 window.open('pdfCustomerBill?invoiceid='+val,'','width=600,height=500,left=300,top=150,scrollbar=yes')
 }
</script>
<title>View Generated Bill</title>
</head>
<%@include file="HeaderPage.jsp" %>
<table broder="0">
<tr><td>
<%@include file="SideMenu.jsp" %>
</td>

<td align="center" width="100%">


<form action="listCustomer" method="get">
<%
    ArrayList<ServiceProvider> providerList = (ArrayList<ServiceProvider>)request.getAttribute("providerList");
	ArrayList<BillInformation> billInfoList = (ArrayList<BillInformation>)request.getAttribute("billInfoList");
%>
<table align="center" bgcolor="#ededed" border="1" width="50%">
    <tr>
        <td colspan="2" align="center" class="pageHeader">View Generated Bill Details</td>
      
    </tr>
    <tr>
        <td class="normalStyleHeader">Provider Name</td>
        <td style="padding: 5px 0 5px 5px;">
<select name="providerId">
<option value="0">(please select:)</option> 
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
<br><br>
<%if(billInfoList.size()>0){ %>
<table align="center" bgcolor="#ededed" border="1" width="80%">
<tr>
	<td class="normalStyleHeader" width="15%">Customer Name</td>
	<td class="normalStyleHeader" width="5%">Phone Number</td>	
	<td class="normalStyleHeader">Smart Meter ID</td>
	<td class="normalStyleHeader" width="10%">Start Date</td>
	<td class="normalStyleHeader" width="10%">End Date</td>
	<td class="normalStyleHeader">Start Meter Reading</td>
	<td class="normalStyleHeader">End Meter Reading</td>
	<td class="normalStyleHeader">Current Meter Reading</td>
	<td class="normalStyleHeader">Rate/Meter Reading</td>
	<td class="normalStyleHeader">Tax</td>
	<td class="normalStyleHeader" width="12%">Billed Amount</td>
	<td class="normalStyleHeader">&nbsp;</td>
</tr>
<% for(BillInformation billInfo : billInfoList) {  %>
	<tr>
		<td class="normalStyle"><%=billInfo.getCust_name()%></td>
		<td class="normalStyle"><%=billInfo.getPhone_num()%></td>
		<td class="normalStyle"><%=billInfo.getMeter_id()%></td>
		<td class="normalStyle"><%=billInfo.getStart_date()%></td>
		<td class="normalStyle"><%=billInfo.getEnd_date()%></td>
		<td class="normalStyle"><%=billInfo.getMinMeterReading()%></td>
		<td class="normalStyle"><%=billInfo.getMaxMeterReading()%></td>
		<td class="normalStyle"><%=billInfo.getTotal_units()%></td>
		<td class="normalStyle"><%=billInfo.getRate()%></td>
		<td class="normalStyle"><%=billInfo.getTax()%></td>
		<td class="normalStyle"><%=billInfo.getTotal()%>&nbsp;<%=billInfo.getCurrency_type()%></td>
		<td class="normalStyle">
			<a href="#" onclick="javascript:showPDF(<%=billInfo.getInvoice_id()%>)">PDF</a>
		</td>
		
	</tr>
<%}%>    
    
</table>
<%}%> 
</form>
</td></tr></table>
</html>
