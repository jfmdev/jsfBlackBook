package io.github.jfmdev.jsfaddressbook.servlets;

import io.github.jfmdev.jsfaddressbook.dal.MyDb;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Logger;

/**
 * Servlet invoked when the server is started.
 * 
 * @author jfmdev
 */
public class InitServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        // Gets current context.
        ServletContext context = this.getServletContext();

        // Gets the database path and save it.
        String fullDbPath = context.getRealPath(MyDb.DB_URL);
        MyDb.setDbFullPath(fullDbPath);
        
        // Initialize logging library.
        String loggingProps = context.getRealPath("/WEB-INF/tinylog.properties");
        try{ 
            Configurator newConfig = Configurator.fromFile(new File(loggingProps)); 
            newConfig.activate();
        }catch(IOException e) {System.err.println(e);}
        
        // Create log entry.
        Logger.info("Initialization servlet executed successfully");
    }
}
