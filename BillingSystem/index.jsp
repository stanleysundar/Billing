 <%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TNEB Billing Service</title>
    </head>
 
    <body> 
    <table>
    	<tr align="center">
    		<td align="center"><h1>TNEB Billing Solution</td>
    	</tr>
    </table>
    	<form action="insertCustomer" method="post">
<table align="center" bgcolor="#99FFCC" border="1" width="70%">
    <tr>
        <td colspan="2" align="center">Add Customer Details </td>
    </tr>
    <tr>
        <td>Name </td>
        <td><input type="text" name="name" maxlength="100"></td>
    </tr>
    <tr>
        <td>SM Id </td>
        <td><input type="text" name="sm_id" maxlength="20"></td>
    </tr>
    <tr>
        <td>Address </td>
        <td><input type="text" name="address" maxlength="200"></td>
    </tr>
    <tr>
        <td>Mobile </td>
        <td><input type="text" name="mobile" maxlength="10"></td>
    </tr>
    <tr>
        <td>EmailId </td>
        <td><input type="text" name="emailid" maxlength="20"></td>
    </tr> 
    <tr>
        <td colspan="2" align="center"><input type="submit" value="Submit"></td>
    </tr> 
</table>
</form>
<table align="center"  width="70%">
  <tr align="center">
        <div align="center">
            <h3>Get the list of Customers </h3>
            <form action="listCustomer" method="post">
                
                <input type="submit" value="List Customers" />
            </form>          
        </div>
    </tr>
   </table>
  <table align="center"  width="70%">
  <tr align="center">
        <div align="center">
            <h3> Choose File to Upload usage data </h3>
            <form action="upload" method="post" enctype="multipart/form-data">
                <input type="file" name="file" />
                <input type="submit" value="upload" />
            </form>          
        </div>
    </tr>
   </table>
    </body>
</html>
