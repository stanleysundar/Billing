<%@page import="com.verizon.bs.forms.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

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
<title>Select Bill Dates</title>
</head>
<body>
<form action="generateBill" method="post">
<table align="center" bgcolor="#FFFFCC" border="1" width="70%">
    <tr>
        <td>Meter ID: <%= request.getParameter("mid") %></td>
        <input name="mid" type = "hidden" value = "<%= request.getParameter("mid") %>"/>
        <input name="name" type = "hidden" value = "<%= request.getParameter("name") %>"/>
        <td>Start Date: <input name="strDate" type="text" id="datepicker" /></td>
         <td>End Date: <input name="endDate" type="text" id="datepicker1" /></td>
    </tr>
    
   
</table>
<table align="center" width="70%">
	<tr align="center">
	<td colspan="2" align="center"><input type="submit" value="Submit"></td>
	<td><button onclick="goBack()">Go Back</button></td>
		
	</tr>
</table>
	
</form>
</body>
</html>
