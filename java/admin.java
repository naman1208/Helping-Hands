/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(urlPatterns = {"/admin"})
public class admin extends HttpServlet {

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
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("</head>");
            out.println("<body bgcolor=#F0F8FF>");
            out.write("<img src='footer.jpg' width='50%' height='100px'><img src='banner.jpg' width='50%' height='100px'><hr>");
            out.write("<table width='100%'><tr><td><a href='admstatewise'>statewise</a></td><td><a href='admallmissing'>details of all missing</a></td><td><a href='admclaims'>Claim Details</a></td><td><a href='admUsers'>User Details</a></td><td><a href='logout'>Logout</a></td></tr></table>");
            out.write("<hr><h1><center>Welcome Admin<br><br><br><img src='b6.jpg'></h1></center>");
            out.write("<br><br><hr><div>\n"+"<h3><center>All Rights Reserved</center>\n<center><address>Copyright 2019</address></center>\n</div>");
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
