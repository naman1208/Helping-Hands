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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
@WebServlet(urlPatterns = {"/admstatewise"})
public class admstatewise extends HttpServlet {

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
            if(hs==null)
                response.sendRedirect("login");
            else
            {
                Object a=hs.getAttribute("UTYPE");
                String s=a.toString();
                if(!s.equals("ADMIN"))
                {
                    response.sendRedirect("login");
                }
            }
            String ss=request.getParameter("b1");
            if(ss!=null && ss.equals("Insert"))
            {
                stmt=con.prepareStatement("Select count(*)+1 from States");
                rs=stmt.executeQuery();
                String cid="";
                if(rs.next())
                {
                    cid=rs.getString(1);
                }
                stmt=con.prepareStatement("Insert into States values(?,?,?,?)");
                stmt.setString(1,cid);
                stmt.setString(2,request.getParameter("t1"));
                stmt.setString(3,request.getParameter("t2"));
                stmt.setString(4,request.getParameter("t3"));
                stmt.executeUpdate();
            }
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("</head>");
            out.println("<body bgcolor=#F0F8FF>");
            out.write("<img src='footer.jpg' width='50%' height='100px'><img src='banner.jpg' width='50%' height='100px'><hr>");
            out.write("<table width='100%'><tr><td><a href='admstatewise'>statewise</a></td><td><a href='admallmissing'>details of all missing</a></td><td><a href='admclaims'>Claim Details</a></td><td><a href='admUsers'>User Details</a></td><td><a href='logout'>Logout</a></td></tr></table>");
            out.write("<hr>");
            out.write("<form method='post'>");
            out.write("<table align='center'>");
            out.write("<tr><td>Category Name:</td><td><input type='text' name='t1'></td></tr>");
            out.write("<tr><td>Details:</td><td><input type='text' name='t2'></td></tr>");
            out.write("<tr><td>Photo:</td><td><input type='file' name='t3'></td></tr>");
            out.write("<tr><td></td><td><input type='submit' name='b1' value='Insert'></td></tr>");
            out.write("</table></form>");
            out.write("<hr>");
            out.write("<table align='center' border='1'>");
            out.write("<tr><th>CID</th><th>Name</th><th>Description</th><th>Photo</th></tr>");
            stmt=con.prepareStatement("Select * from States");
            rs=stmt.executeQuery();
            while(rs.next())
            {
                out.write("<tr>");
                out.write("<td>"+rs.getString(1)+"</td>");
                out.write("<td>"+rs.getString(2)+"</td>");
                out.write("<td>"+rs.getString(3)+"</td>");
                out.write("<td><img src='"+rs.getString(4)+"' width='50px'></td>");
                out.write("</tr>");
            }
            out.write("</table>");
            out.println("</body>");
            out.println("</html>");
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
