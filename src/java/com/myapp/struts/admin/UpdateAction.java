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
public class UpdateAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String UPDATED = "updated";

    static final String JDBC_DRIVER ="com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/attdb?zeroDateTimeBehavior=convertToNull";
    static final String dbuser = "root";
    static final String dbpassw = "";
    int id;
    String number;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try
	{
                    UpdateForm update = (UpdateForm)form;
                    id = update.getId();
                    number = update.getNumber();
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection (DB_URL, dbuser, dbpassw);
                    Statement st = conn.createStatement();
                    st.executeUpdate("update employee set mob= '"+number+"' where id="+id+"");     
        }
            catch(Exception e){
                e.printStackTrace();
            }
        return mapping.findForward(UPDATED);
    }
}
