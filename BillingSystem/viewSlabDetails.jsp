<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.verizon.bs.forms.SlabDetails"%>    
    <%@ page import ="java.util.*" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="mainstyle.css" rel="stylesheet" type="text/css" />
<title>Service Provider Slab Details</title>
</head>
<%@include file="HeaderPage.jsp" %>
<table broder="0">
<tr><td>
<%@include file="SideMenu.jsp" %>
</td>

<td align="center" width="100%">
<br><br><br>
<%
    ArrayList<SlabDetails> slabList = (ArrayList<SlabDetails>)request.getAttribute("slabList");	
%>
<table align="center" bgcolor="#ededed" border="1" width="50%">
    <tr>
        <td colspan="4" align="center" class="pageHeader">Service Provider Slab Details</td>
      
    </tr>
    <tr>
        <td class="normalStyleHeader">Provider Name</td>
        <td class="normalStyleHeader">Start Reading</td>
        <td class="normalStyleHeader">End Reading</td>
        <td class="normalStyleHeader">Rate for this Slab</td>
    </tr>
    
<% for(SlabDetails sd : slabList) {  %>
	<tr>
		<td class="normalStyle"><%=sd.getProvider_name()%></td>
		<td class="normalStyle"><%=sd.getStartReading()%></td>
		<td class="normalStyle"><%=sd.getEndReading()%></td>
		<td class="normalStyle"><%=sd.getRate()%></td>	
	</tr>
<%}%>    
</table>

</td></tr></table>
</html>
