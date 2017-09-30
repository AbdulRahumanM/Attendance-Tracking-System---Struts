<%-- 
    Document   : track
    Created on : 21 Aug, 2015, 5:36:14 PM
    Author     : Administrator
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Attendance Log</title>
    </head>
    <body>
        <h1>Attendance Details</h1>
        <h4>Employee ID:</h4>
        <% 
            String trackId = String.valueOf(request.getAttribute("TRACK_ID"));
            Integer x = Integer.valueOf(trackId);
            out.print(x);
            Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/attdb?zeroDateTimeBehavior=convertToNull", "root", "");

            Statement statement = connection.createStatement() ;
            ResultSet resultset = statement.executeQuery("select dates,intime,outtime,(outtime-intime)/10000 as workhour from logger where id = "+x+""); 
        %>
        <table border="1" width ="500">
            <tr bgcolor="olivegreen">
            <th>Date</th>
            <th>In Time</th>
            <th>Out Time</th>
            <th>Work Hours</th>
            </tr>
            <% while(resultset.next()){ %>
            <tr>
                <td align="center"> <%= resultset.getString(1) %></td>
                <td align="center"> <%= resultset.getString(2) %></td>
                <td align="center"> <%= resultset.getString(3) %></td>
                <td align="center"> <%= resultset.getString(4) %> hrs</td>
            </tr>
            <% } %>
       <% connection.close(); 
          statement.close();
        %>
        </table>
        <% 
           Connection connper = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/attdb?zeroDateTimeBehavior=convertToNull", "root", "");

            Statement stper = connper.createStatement() ;
            ResultSet rsper = stper.executeQuery("select changes from behaviour where id = "+x+""); 
        %>
        <br>
        <table border="0" >
            <tr bgcolor="Red">
                <th><b>Behaviour changes:</b></th>
            </tr>
            <% while(rsper.next()){ %>
            <tr>
                <td align="left">Behaviour change: Productivity time changed to <b> <%= rsper.getString(1) %> hours </b> </td>
                </tr>
            <% } %>
       <% connper.close(); 
          stper.close();
        %>
        </table>
        <% 
           Connection conntre = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/attdb?zeroDateTimeBehavior=convertToNull", "root", "");

            Statement sttre = conntre.createStatement() ;
            ResultSet rstre = sttre.executeQuery("select distinct inc from trend where id = "+x+""); 
        %>
        <br>
        <table border="0" >
            <tr bgcolor="blue">
                <th><b>Trends:</b></th>
            </tr>
            <% while(rstre.next()){ %>
            <tr>
                <td align="left">New Trend: Intime changes to <b> <%= rstre.getString(1) %> </b> </td>
                </tr>
            <% } %>
       <% conntre.close(); 
          sttre.close();
        %>
        </table>

        <a href="/Employee/home.jsp">Home</a>
        <a href="/Employee/index.jsp">Logout</a>
    </body>
</html>
