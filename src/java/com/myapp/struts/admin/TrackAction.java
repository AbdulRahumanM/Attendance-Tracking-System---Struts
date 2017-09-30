/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Administrator
 */
public class TrackAction extends org.apache.struts.action.Action {

    private static final String TRACKING = "tracking";
    static final String JDBC_DRIVER ="com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/attdb?zeroDateTimeBehavior=convertToNull";
    static final String dbuser = "root";
    static final String dbpassw = "";
    int id;
    Time cast1;
    String inc;
    int in_trend,in_temp,in_temp1,in_temp2;
    double behaviour,temp,temp1,temp2;
    int c=1,a=1;
    ArrayList<Float> wh = new ArrayList<Float>();
    ArrayList<String> in = new ArrayList<String>();
    ArrayList<Integer> innew = new ArrayList<Integer>();
    ArrayList<String> out = new ArrayList<String>();
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try
	{
                    TrackForm trackid = (TrackForm)form;
                    id = trackid.getId();
                    request.setAttribute("TRACK_ID", id);   
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn1 = DriverManager.getConnection (DB_URL, dbuser, dbpassw);
                    Statement st1 = conn1.createStatement();
                    String tql = "SELECT round((outtime-intime)/10000,1) as workhour from logger where id="+ id +"";
                    ResultSet rs = st1.executeQuery(tql);
                    while(rs.next()){
                        wh.add(rs.getFloat(1));
                    }
                    st1.close();
                    Statement st3 = conn1.createStatement();
                    st3.executeUpdate("delete from behaviour where id="+id+"");
                    st3.close();
                    behaviour = wh.get(0);
                    temp1= behaviour+0.3;
                    temp2= behaviour-0.3;
                    while(c<wh.size())
                    {
                        if(wh.get(c)>=temp1 || wh.get(c)<=temp2){
                            temp = wh.get(c);
                            if((wh.get(c+1)>=temp-0.08 || wh.get(c+1)<=temp+0.08) && (wh.get(c+2)>=temp-0.08 || wh.get(c+2)<=temp+0.08 ) && (wh.get(c+3)>=temp-0.08 || wh.get(c+3)<=temp+0.08)  && (wh.get(c+4)>=temp-0.08 || wh.get(c+4)<=temp+0.08 )){
                            behaviour= (temp+wh.get(c+1)+wh.get(c+2)+wh.get(c+3)+wh.get(c+4))/5;
                            temp1=behaviour+0.3;
                            temp2=behaviour-0.3;
                            //Connection conn2 = DriverManager.getConnection (DB_URL, dbuser, dbpassw);
                        Statement st2 = conn1.createStatement();
                        st2.executeUpdate("insert into behaviour values("+ id +",'"+ behaviour +"')");
                        st2.close();
                        //conn2.close();
                            }
                     /*       else if(wh.get(c+1)!=temp && wh.get(c+2)==temp && wh.get(c+3)== temp && wh.get(c+4)==temp)
                            {
                                behaviour = temp;
                                temp1 = behaviour + 0.3;
                                temp2 = behaviour - 0.3;
                                Statement sta1 = conn1.createStatement();
                                sta1.executeUpdate("insert into behaviour values("+ id +",'"+behaviour+"')");
                                sta1.close();
                            }
                            else if(wh.get(c+1)== temp && wh.get(c+2)!= temp && wh.get(c+3)==temp && wh.get(c+4)== temp){
                                behaviour = temp;
                                temp1 = behaviour + 0.3;
                                temp2 = behaviour - 0.3;
                                Statement sta2 = conn1.createStatement();
                                sta2.executeUpdate("insert into behaviour values("+id+",'"+behaviour+"')");
                                sta2.close();
                            }
                            else if(wh.get(c+1)== temp && wh.get(c+2)== temp && wh.get(c+3)!= temp && wh.get(c+4)== temp){
                                behaviour = temp;
                                temp1 = behaviour + 0.3;
                                temp2 = behaviour - 0.3;
                                Statement sta3 = conn1.createStatement();
                                sta3.executeUpdate("insert into behaviour values ("+id+",'"+behaviour+"')");
                                sta3.close();
                            }
                            else if(wh.get(c+1)== temp && wh.get(c+2) == temp && wh.get(c+3)== temp && wh.get(c+4)!= temp){
                                behaviour = temp;
                                temp1 = behaviour + 0.3;
                                temp2 = behaviour - 0.3;
                                Statement sta4 = conn1.createStatement();
                                sta4.executeUpdate("insert into behaviour values ("+id+",'"+behaviour+"')");
                                sta4.close();
                            }*/
                       //     else if(wh.get(c+1)!= temp && wh.get(c+2)==temp && wh.get(c+3)!= temp){
                                    
                         //   }
                           // }
                            
                       
                    }
                    c++;
                            }
                       /* if ((wh.get(c)> behaviour[bcnt]+0.2)|| (wh.get(c)< behaviour[bcnt]-0.2))
                        {
                            temp= wh.get(c);
                            cnt++;
                        }   
                            //if (hr[c+1]!= temp){
                              //  temp = 0;
                                //cnt=0;
                            //}
                            //else if(hr[c+1]==temp){
                            if(cnt>0){
                            for(int j = c+1;j<c+4;j++)
                            {
                                
                                if(wh.get(j)==temp){
                                    cnt++;
                                    if(cnt==5)
                                    {
                                        bcnt++;
                                        behaviour[bcnt]=temp;
                                        temp=0;
                                        cnt=0;
                                    }
                                }
                                else{
                                    cnt=0;
                                    temp=0;
                                    j=c+5;
                                }
                            }
                            }
                            //}   
                    }
*/
                    //Connection conn3 = DriverManager.getConnection (DB_URL, dbuser, dbpassw);
                    Statement st4 = conn1.createStatement();
                    String inql = "SELECT intime,outtime from logger where id="+ id +"";
                    ResultSet rsin = st4.executeQuery(inql);
                    while(rsin.next()){
                        in.add(rsin.getString(1));
			out.add(rsin.getString(2));
                    }
                    st4.close();
                   // conn3.close();
                    for(int i=0;i<in.size();i++){
                        String s = in.get(i);
                        String s1 = s.substring(0, 1);
                        String s2 = s.substring(3, 4);
                        int i1 = Integer.parseInt(s1);
                        int i2 = Integer.parseInt(s2);
                        i1=i1*3600000;
                        i2=i2*600000;
                        int i3 = i1+i2;
                        innew.add(i3);
                    }
                    //Connection conn4 = DriverManager.getConnection (DB_URL, dbuser, dbpassw);
                    Statement st5 = conn1.createStatement();
                    st5.executeUpdate("delete inc from trend where id="+id+"");
                    st5.close();
                    //conn4.close();
                    in_trend= innew.get(0);
                    //int time1=1800000;
                    //SimpleDateFormat timeFormat = new SimpleDateFormat("ssssss");
                    //Date date1 = timeFormat.parse(time1);
		    in_temp1= in_trend + 1800000;
                    in_temp2= in_trend - 1800000;
                    while(a<innew.size())
                    {
                        if((innew.get(a)-in_temp2)>=1800000 || (in_temp1-innew.get(a))>=1800000){
                            in_temp = innew.get(a);
                            if(innew.get(a+1)==in_temp && innew.get(a+2)==in_temp && innew.get(a+3)==in_temp && innew.get(a+4)==in_temp){
                            in_trend = in_temp;
                            in_temp1= in_trend + 1800000;
                            in_temp2= in_trend - 1800000;
			    inc = in.get(a);
                            Statement st6 = conn1.createStatement();
                        st6.executeUpdate("insert into trend values("+ id +",'"+ inc +"')");
                        st6.close();
                            }
                    }
                    a++;
                            }
                  //  SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss");
		//	    String inc = ft.format(new Date(in_trend));
                    
                      //  Connection conn5 = DriverManager.getConnection (DB_URL, dbuser, dbpassw);
                        
                        conn1.close();
        }
            catch(Exception e){
                e.printStackTrace();
            }
        return mapping.findForward(TRACKING);
    }
}
