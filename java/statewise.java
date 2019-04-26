import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/statewise"})
public class statewise extends HttpServlet {
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
                    reg="<td><a href='register'>Register</a></td>";
                }
                PrintWriter out=response.getWriter();
                response.setContentType("text/html");
                out.write("<html><body bgcolor=#FAEBD7>");
                out.write("<center><img src='banner.jpg' width='50%' height='120px'></center><hr>");
                out.write("<table width='100%'><tr><td><a href='index'>Home</a></td><td><a href='statewise'>State-Wise</a></td><td><a href='allmissing'>All Missing</a></td><td><a href='help'>Contact Us/Help</a></td><td><a href='myaccount'>My Account</a></td>"+reg+"<td>"+ss+"</td><td><a href='claims'>Claims</a></td></tr></table><hr>");
                stmt=con.prepareStatement("select count(*) from States");
                int n=0;
                rs=stmt.executeQuery();
                if(rs.next()) n=rs.getInt(1);
                n=(int)Math.ceil(n/3.0);
                out.write("<table align='center'>");
                stmt=con.prepareStatement("select * from States");
                rs=stmt.executeQuery();
                for(int i=1;i<=n;i++)
                {
                    out.write("<tr align='center'>");
                    if(rs.next())
                    {
                        String s1=rs.getString(1);
                        String s2=rs.getString(2);
                        String s3=rs.getString(3);
                        String s4=rs.getString(4);
                        out.write("<td>");
                        out.write("<a href='allmissing?cid="+s1+"'><img src='"+s4+"' width='300px'></a>");
                        out.write("<br>"+s2+"<br>"+s3);
                        out.write("</td>");
                    }
                    else
                    {
                        out.write("<td></td>");
                    }
                    if(rs.next())
                    {
                        String s1=rs.getString(1);
                        String s2=rs.getString(2);
                        String s3=rs.getString(3);
                        String s4=rs.getString(4);
                        out.write("<td>");
                        out.write("<a href='allmissing?cid="+s1+"'><img src='"+s4+"' width='300px'></a>");
                        out.write("<br>"+s2+"<br>"+s3);
                        out.write("</td>");
                    }
                    else
                    {
                        out.write("<td></td>");
                    }
                    if(rs.next())
                    {
                        String s1=rs.getString(1);
                        String s2=rs.getString(2);
                        String s3=rs.getString(3);
                        String s4=rs.getString(4);
                        out.write("<td>");
                        out.write("<a href='allmissing?cid="+s1+"'><img src='"+s4+"' width='300px'></a>");
                        out.write("<br>"+s2+"<br>"+s3);
                        out.write("</td>");
                    }
                    else
                    {
                        out.write("<td></td>");
                    }

                    out.write("</tr>");
                }
                out.write("</table>");
                out.write("<hr><img src='footer.jpg' width='100%' height='150px'>");
                out.write("</body></html>");
        }catch(Exception ee){}
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
