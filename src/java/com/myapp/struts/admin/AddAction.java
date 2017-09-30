/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Administrator
 */
public class AddAction extends org.apache.struts.action.Action {

    private static final String ADDED = "added";
    static final String JDBC_DRIVER ="com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/attdb?zeroDateTimeBehavior=convertToNull";
    static final String dbuser = "root";
    static final String dbpassw = "";
    String name;
    String number;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try
	{
                    AddForm add = (AddForm)form;
                    name = add.getName();
                    number = add.getNumber();
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection (DB_URL, dbuser, dbpassw);
                    Statement st1 = conn.createStatement();
                    st1.executeUpdate("INSERT INTO Employee (name,mob,password) values('"+ name +"','"+ number +"','"+name+"123')");     
        }
            catch(Exception e){
                e.printStackTrace();
            }
        return mapping.findForward(ADDED);
    }
}

