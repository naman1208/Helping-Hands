import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import javax.servlet.http.*;
@WebServlet(urlPatterns = {"/index"})
public class index extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    {
        try{
                Class.forName("org.gjt.mm.mysql.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/lostandfound","root","");
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
                out.write("<marquee scrollamount='20' height='200px' onmouseover='this.stop()' onmouseout='this.start()'>");
                out.write("<img src='b2.jpg' width='400px' height='200px'>");
                out.write("<img src='database.jpg' width='400px' height='200px'>");
                out.write("<img src='fir.jpg' width='400px' height='200px'>");
                out.write("<img src='thanks.jpg' width='400px' height='200px'>");
                out.write("<img src='contact.jpg' width='400px' height='200px'>");
                out.write("<img src='b8.jpg' width='400px' height='200px'>");
                out.write("</marquee>");
                out.write("<marquee scrollamount='20' direction='right' height='205px' onmouseover='this.stop()' onmouseout='this.start()'>");
                out.write("<img src='b4.jpg' width='400px' height='205px'><img src='m3.jpg' width='400px' height='205px'><img src='m4.jpg' width='400px' height='205px'>");
                out.write("<img src='m5.jpg' width='400px' height='205px'><img src='l1.jpg' width='400px' height='205px'><img src='b6.jpg' width='400px' height='205px'>");              
                out.write("<img src='b7.jpg' width='400px' height='205px'><img src='m6.jpg' width='400px' height='205px'><img src='b9.jpg' width='400px' height='205px'><br>");
                out.write("</marquee>");
                out.write("<hr><div>\n"+"<center>All Rights Reserved</center>\n<center><address>Copyright 2019</address></center>\n</div>");
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
