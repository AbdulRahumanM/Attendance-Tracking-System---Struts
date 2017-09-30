<%-- 
    Document   : emphome
    Created on : 21 Aug, 2015, 10:16:49 AM
    Author     : Administrator
--%>
<% Class.forName("com.mysql.jdbc.Driver"); %>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="EmployeeForm" scope="request" class="com.myapp.struts.employee.EmployeeForm"></jsp:useBean>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Portal</title>
    </head>
    <body>
        <% String name = EmployeeForm.getEmpname();
           int id = EmployeeForm.getId();
        out.print("<h3>Welcome "+name+" </h3>");
        Date date = new Date();
        String dates =null;
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/attdb?zeroDateTimeBehavior=convertToNull", "root", "");

            Statement st = connection.createStatement();
            Statement st1 = connection.createStatement();
            ResultSet rs = st1.executeQuery("select dates from tracker where id = "+id+" order by dates desc limit 1");
            while(rs.next()){
                dates = rs.getString(1);
            }
            out.print("Last Login: "+ dates +" ");
            st.executeUpdate("insert into tracker values ('"+date+"','y',"+id+")");
         %>
    </body>
    <br>
    <a href="/Employee/logout.jsp">Logout</a>
</html>
