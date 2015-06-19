package io.github.jfmdev.jsfblackbook.servlets;

import io.github.jfmdev.jsfblackbook.dal.Contact;
import io.github.jfmdev.jsfblackbook.dal.MyDb;
import io.github.jfmdev.jsfblackbook.dal.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 * A servlet that allows to test the connectivity with the database.
 * 
 * @author jfmdev
 */
public class TestDbServlet extends HttpServlet {
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Initialize variables.
        String error = null;
        int userCount = 0;
        int contactCount = 0;
        
        try {
            // Get the path to the database.
            ServletContext context = this.getServletContext();
            String fullDbPath = context.getRealPath(MyDb.DB_URL);

            // Execute queries
            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + fullDbPath);
            QueryRunner run = new QueryRunner();
            List<User> users = (List<User>)run.query(conn, "SELECT * FROM user", new BeanListHandler(User.class));
            List<Contact> contacts = (List<Contact>)run.query(conn, "SELECT * FROM contact", new BeanListHandler(Contact.class));
            
            // Print results.
            //for(int i=0; i<users.size(); i++) System.out.println(users.get(i));
            //for(int i=0; i<contacts.size(); i++) System.out.println(contacts.get(i));
            
            // Save counts.
            userCount = users.size();
            contactCount = contacts.size();
        }catch(SQLException e) {
            // Save error message.
            error = e.getMessage();
        }
        
        // Show XML with data.
        response.setContentType("text/xml;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<?xml version=\"1.0\" standalone=\"yes\" ?>");
            out.println("<dbInfo>");
            if(error == null) {
                out.println("<contact>" + contactCount + "</contact>");
                out.println("<user>" + userCount + "</user>");
            } else {
                out.println("<error>" + error + "</error>"); 
            }
            out.println("</dbInfo>");
        }
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        return "Servlet that returns the current version of the software";
    }
    // </editor-fold>
}
