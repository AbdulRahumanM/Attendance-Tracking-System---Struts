<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!DOCTYPE html>
<html:html>
    
<head>
	
<title>Employee Management System</title>

</head>

<body>

<center>

<div id="login-form">

<html:form action="/adminlogin">

<table align="center" width="400" border="0">

<tr>

<td><input type="text" name="name" placeholder="Your Name"/></td>

</tr>

<tr>

<td><input type="password" name="password" placeholder="Your Password" /></td>

</tr>

<tr>
    <td><input type="submit" value="Login" /></td>

</tr>

</table>

</html:form>

</div>

</center>

</body>

</html:html>