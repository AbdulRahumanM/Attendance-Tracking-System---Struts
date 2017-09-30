/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.admin;

import static com.myapp.struts.admin.loginAction.DB_URL;
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
public class DeleteAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String DELETED = "deleted";
    static final String JDBC_DRIVER ="com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/attdb?zeroDateTimeBehavior=convertToNull";
    static final String dbuser = "root";
    static final String dbpassw = "";
    int id;
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
                    DeleteForm del = (DeleteForm)form;
                    id = del.getId();
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection (DB_URL, dbuser, dbpassw);
                    Statement st2 = conn.createStatement();
                    String dql = "DELETE FROM Employee where id ="+ id +"";
                    st2.executeUpdate(dql);
        }
            catch(Exception e){
                e.printStackTrace();
            }
        return mapping.findForward(DELETED);
    }
}
