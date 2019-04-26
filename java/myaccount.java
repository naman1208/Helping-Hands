import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet(urlPatterns = {"/myaccount"})
public class myaccount extends HttpServlet {
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
                String reg="";
                if(hs==null)
                {
                    reg="<a href='register'>Register</a>";
                }
                PrintWriter out=response.getWriter();
                response.setContentType("text/html");
                out.write("<html><body bgcolor=#FAEBD7>");
                out.write("<center><img src='banner.jpg' width='50%' height='120px'></center><hr>");
                out.write("<table width='100%'><tr><td><a href='index'>Home</a></td><td><a href='statewise'>State-Wise</a></td><td><a href='allmissing'>All Missing</a></td><td><a href='help'>Contact Us/Help</a></td><td><a href='myaccount'>My Account</a></td>"+reg+"<td>"+ss+"</td><td><a href='claims'>Claims</a></td></tr></table><hr>");
                //String ss="<a href='login'>Login</a>";
                out.write("<h3>Details</h3>");
                if(hs==null)
                {
                out.write("<b><center><a href='login'>YOU NEED TO LOGIN FIRST</a></center></b><hr>");
                }
                else
                {
                    Object obj=hs.getAttribute("EMAIL");
                    String str=obj.toString();
                    //out.write("email is"+str);
                    out.write("<table align='center' border='1'>");
                                out.write("<tr><th>Email</th><th>Name</th><th>Mobile</th><th>Address</th><th>State</th><th>City</th><th>Pin Code</th></tr>");
                                stmt=con.prepareStatement("Select * from Members where Email=?");
                                stmt.setString(1,str);
                                rs=stmt.executeQuery();
                                while(rs.next())
                                {
                                    out.write("<tr>");
                                    out.write("<td>"+rs.getString(1)+"</td>");
                                    out.write("<td>"+rs.getString(2)+"</td>");
                                    out.write("<td>"+rs.getString(3)+"</td>");
                                    out.write("<td>"+rs.getString(4)+"</td>");
                                    out.write("<td>"+rs.getString(5)+"</td>");
                                    out.write("<td>"+rs.getString(6)+"</td>");
                                    out.write("<td>"+rs.getString(7)+"</td>");
                                    out.write("</tr>");
                                }
                    out.write("</table>");
                    out.write("<hr><h3>Status of CLAIMS</h3>");
                    out.write("<table align='center' border='1'>");
                    out.write("<tr><th>Order Id</th><th>Date</th><th>Email</th><th>CurrentStatus</th><th></th></tr>");
                    stmt=con.prepareStatement("Select * from Claims where Email=?");
                    stmt.setString(1,str);
                    rs=stmt.executeQuery();
                    while(rs.next())
                    {
                        out.write("<tr>");
                        out.write("<td>"+rs.getString(1)+"</td>");
                        out.write("<td>"+rs.getString(2)+"</td>");
                        out.write("<td>"+rs.getString(3)+"</td>");
                        out.write("<td>"+rs.getString(4)+"</td>");
                        //out.write("<td>Change Status</td>");
                        out.write("</tr>");
                    }
                    out.write("</table>");
                }
                out.write("<hr><center><img src='footer.jpg' width='50%' height='150px'></center>");
                out.write("</body></html>");
        }catch(Exception ee){}
    }
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
