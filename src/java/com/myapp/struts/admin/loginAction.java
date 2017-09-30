/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;
/**
 *
 * @author Administrator
 */
public class loginAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";
    static final String JDBC_DRIVER ="com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/attdb?zeroDateTimeBehavior=convertToNull";
    String name;
    String password;
     
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
                    AdminForm data = (AdminForm)form;
                    name = data.getName();
                    password = data.getPassword();
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection (DB_URL, dbuser, dbpassw);
                    Statement st = conn.createStatement();
                    String sql = "SELECT * FROM Admin where name='"+ name +"' and password='"+ password +"'";
                    ResultSet res = st.executeQuery(sql);
                	if (res.next()) 
			{
                            conn.close();
                            return mapping.findForward(SUCCESS);
                        }
                        else{
                            conn.close();
                            return mapping.findForward(FAILURE);
                        }
        }
            catch(Exception e){
                e.printStackTrace();
            }
        return null;
    }
}