 <%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="mainstyle.css" rel="stylesheet" type="text/css" />
        <title>Upload Meter Reading</title>
    </head>
 
<%@include file="HeaderPage.jsp" %>
<table broder="0">
<tr><td>
<%@include file="SideMenu.jsp" %>
</td>

<td align="center" width="100%">
<br><br><br><br><br><br>

<table align="center" bgcolor="#ededed" border="1" width="50%">
    <tr>
        <td colspan="2" align="center" class="pageHeader">Upload Usage Data </td>
    </tr>
     <tr>
        <td class="normalStyleHeader" valign="top">Choose File to Upload </td>
        <td style="padding: 5px 0 5px 5px;">
        	<form action="upload" method="post" enctype="multipart/form-data">
                <input type="file" name="file" />                
            </form>
        </td>
    </tr>
    <tr>
        <td colspan="2" align="center" style="padding: 5px 0 5px 5px;"><input type="submit" value="Upload"></td>
    </tr>
  </table>

</td></tr></table>
</html>
