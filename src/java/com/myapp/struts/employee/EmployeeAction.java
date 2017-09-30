/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Administrator
 */
public class EmployeeAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";
    static final String JDBC_DRIVER ="com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/attdb?zeroDateTimeBehavior=convertToNull";
    int id;
    String password;
    String empname;
     
    static final String dbuser = "root";
    static final String dbpassw = "";

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        try
	{
                    EmployeeForm emp = (EmployeeForm)form;
                    id = emp.getId();
                    password = emp.getPassword();
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn1 = DriverManager.getConnection (DB_URL, dbuser, dbpassw);
                    Statement st3 = conn1.createStatement();
                    String eql = "SELECT * FROM employee where id="+ id +" and password='"+ password +"'";
                    ResultSet rs = st3.executeQuery(eql);
                	if (rs.next()) 
			{
                          conn1.close();
                          Connection conn2 = DriverManager.getConnection (DB_URL, dbuser, dbpassw);
                          Statement st4 = conn2.createStatement();
                          String nql = "SELECT * FROM employee where id="+ id +" and password='"+ password +"'";
                          ResultSet rs1 = st4.executeQuery(nql);
                          if(rs1.next())
                          {
                               empname = rs1.getString(1);
                               emp.setEmpname(empname);
                          }
                          return mapping.findForward(SUCCESS);
                        }
                        else{
                            conn1.close();
                            return mapping.findForward(FAILURE);
                        }
        }
            catch(Exception e){
                e.printStackTrace();
            }
        return null;
    }
}
