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
@WebServlet(urlPatterns = {"/claims"})
public class claims extends HttpServlet {
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
                String[] pids=val.split("#");
                out.write("<table align='center'>");
                out.write("<tr><th>S.No.</th><th>Name</th><th>Description</th><th>Gender</th><th>Station</th><th></th></tr>");
                int sum=0;
                for(int i=1;i<pids.length;i++)
                {
                    stmt=con.prepareStatement("Select * from missingpersons where pid=?");
                    stmt.setString(1,pids[i]);
                    rs=stmt.executeQuery();
                    if(rs.next())
                    {
                        out.write("<tr>");
                        out.write("<td>"+i+"</td>");
                        out.write("<td>"+rs.getString(3)+"</td>");
                        out.write("<td>"+rs.getString(4)+"</td>");
                        out.write("<td>"+rs.getString(5)+"</td>");
                        out.write("<td>"+rs.getString(6)+"</td>");
                        String s=rs.getString("photo");
                        out.write("<td><img src='"+s+"' width='50px'></td>");
                        out.write("</tr>");
                    }

                }
                out.write("<tr><th colspan='6'>Don't make fake claims</th></tr>");
                out.write("</table><hr>");
                if(hs==null)
                {
                    out.write("<center><a href='login'>Login to Confirm your Claims</a></center>");
                }
                else
                {
                    out.write("<center><a href='claimconfirm'>Confirm your Claims</a></center>");
                }
                out.write("<hr><center><img src='footer.jpg' width='50%' height='150px'></center>");
                out.write("</body></html>");
        }catch(Exception ee){
        System.out.println("error is:"+ee);}
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
