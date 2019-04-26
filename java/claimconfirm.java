/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
@WebServlet(urlPatterns = {"/claimconfirm"})
public class claimconfirm extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    {
        try{
                Class.forName("org.gjt.mm.mysql.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/LostAndFound","root","");
                PreparedStatement stmt;
                ResultSet rs;
                HttpSession hs=request.getSession(false);
                String ss="<a href='login'>Login</a>";
                if(hs!=null)
                {
                    ss="<a href='logout'>Logout</a>";
                }
                PrintWriter out=response.getWriter();
                response.setContentType("text/html");
                out.write("<html><body>");
                out.write("<center><img src='banner.jpg' width='50%' height='120px'></center><hr>");
                out.write("<table width='100%'><tr><td><a href='index'>Home</a></td><td><a href='statewise'>State-Wise</a></td><td><a href='allmissing'>All Missing</a></td><td><a href='help'>Contact Us/Help</a></td><td><a href='myaccount'>My Account</a></td><td>"+ss+"</td><td><a href='claims'>Claims</a></td></tr></table><hr>");
                String val="";
                Cookie[] ck=request.getCookies();
                if(ck!=null)
                {
                    for(int i=0;i<ck.length;i++)
                    {
                        String nm=ck[i].getName();
                        if(nm.equals("Claim"))
                        {
                            val=ck[i].getValue();
                        }
                    }
                }
                stmt=con.prepareStatement("Select count(*)+1 from claims");
                String cid="",sta="";

                rs=stmt.executeQuery();
                if(rs.next()) cid=rs.getString(1);
                java.util.Date d=new java.util.Date();
                String dt=(d.getYear()+1900)+"-"+(d.getMonth()+1)+"-"+d.getDate();

                String[] pids=val.split("#");
                int sum=0;
                for(int i=1;i<pids.length;i++)
                {
                    stmt=con.prepareStatement("Select * from missingpersons where pid=?");
                    stmt.setString(1,pids[i]);
                    rs=stmt.executeQuery();
                    if(rs.next())
                    {
                        sta=rs.getString("station");
                        stmt=con.prepareStatement("Insert into claimsdetails values(?,?,?,?)");
                        stmt.setString(1,cid);
                        stmt.setString(2,pids[i]);
                        stmt.setString(3,rs.getString("pname"));
                        stmt.setString(4,rs.getString("station"));
                        stmt.executeUpdate();
                    }

                }
                stmt=con.prepareStatement("Insert into claims values(?,?,?,?)");
                stmt.setString(1,cid);
                stmt.setString(2,dt);
                stmt.setObject(3,hs.getAttribute("EMAIL"));
                stmt.setString(4,"Unverified");
                stmt.executeUpdate();
                Cookie c=new Cookie("Claim","XYZ");
                c.setMaxAge(-1);
                response.addCookie(c);
                out.write("<center><img src='thanks.jpg' width='400px' height='200px'></center>");
                out.write("<center><h3>All Claims Will Be Verified By Our Team and you will be contacted shortly</h3></center>");
                out.write("<center><h3>Your ClaimeId is "+cid+"</h3></center>");
                out.write("<center><h3>In case of any problem please contact us</h3></center>");
                out.write("<center><h3>Raise A HELPING HAND</h3></center>");
                out.write("<hr><img src='footer.jpg' width='100%' height='150px'>");
                out.write("</body></html>");
        }catch(Exception ee){System.out.println(ee);}
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
