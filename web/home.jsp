<%@page import="java.sql.*"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<% Class.forName("com.mysql.jdbc.Driver"); %>
<%-- 
    Document   : home
    Created on : 20 Aug, 2015, 6:33:50 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Management Portal</title>
    </head>
    <body>
        <h3>Employee Details</h3>
        <% 
            Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/attdb?zeroDateTimeBehavior=convertToNull", "root", "");

            Statement statement = connection.createStatement() ;
            ResultSet resultset = statement.executeQuery("select * from employee") ; 
        %>
        
        <table border="1" width ="500">
            <tr bgcolor="olivegreen">
            <th>Employee ID</th>
            <th>Mobile Number</th>
            <th>Employee Name</th>
            </tr>
            <% while(resultset.next()){ %>
            <tr>
                <td align="center"> <%= resultset.getString(3) %></td>
                <td align="center"> <%= resultset.getString(2) %></td>
                <td align="center"> <%= resultset.getString(1) %></td>
            </tr>
            <% } %>
       <% connection.close(); 
          statement.close();
        %>
        </table>
       <h3>Delete Employee</h3>
       <html:form action="/deleteref" method="POST">
           <input type="text" name="id" placeholder ="Enter the Employee ID" />
           <input type="submit" value="Delete" />
       </html:form>
       <h3>New Employee</h3>
       <html:form action="/newemp" method="GET">
           <input type="text" name="name" placeholder ="Enter the Employee Name" /><br>
           <input type="text" name="number" placeholder ="Enter the Mobile Number" /><br>
           <input type="submit" value="Add" />
       </html:form>
       <h3>Update Master Details</h3>
       <html:form action="/update">
           <input type="text" name="id" placeholder ="Enter the Employee ID" /><br>
           <input type="text" name="number" placeholder ="New Mobile Number" /><br>
           <input type="submit" value="Update" />
           <h3>Track Attendance Details</h3>
       </html:form>
       <html:form action="/track">
           <input type="text" name="id" placeholder ="Enter the Employee ID" /><br>
           <input type="submit" value="Track" />
       </html:form>
           <a href="/Employee/index.jsp">Logout</a>

    </body>
</html:html>