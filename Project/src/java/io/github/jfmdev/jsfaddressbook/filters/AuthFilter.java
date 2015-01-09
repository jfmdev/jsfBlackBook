package io.github.jfmdev.jsfaddressbook.filters;

import io.github.jfmdev.jsfaddressbook.beans.SessionData;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.pmw.tinylog.Logger;

/**
 * Authentication filter for verify if the user has the required privileges to see the pages that it opens.
 * 
 * @author jfmdev
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthFilter implements Filter {
     
    public AuthFilter() {
    }
 
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
         
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
         try {
            // Get request, response and session objects.
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            HttpSession httpSession = httpRequest.getSession(false);
            SessionData sessionData = httpSession != null? (SessionData) httpSession.getAttribute("sessionData") : null;
            boolean isAdmin = sessionData != null && sessionData.isAdmin();
            boolean isLogged = sessionData != null && sessionData.isLogged();
            
            //  Verify if the user has permission to the requested page.
            String requestedUri = httpRequest.getRequestURI();
            if ( !isAdmin && (requestedUri.contains("/contact-list.xhtml") || requestedUri.contains("/user-list.xhtml")) ||
                 !isLogged && (requestedUri.contains("/contact-edit.xhtml") || requestedUri.contains("/contact-personal-list.xhtml") || requestedUri.contains("/user-edit.xhtml")) ){
                // Redirect to the login page.
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/faces/login.xhtml");
            } else {
                // Continue to the page.
                chain.doFilter(request, response);
            }
      }
     catch(IOException|ServletException e) {
         Logger.error(e);
     }
    }
 
    @Override
    public void destroy() {
         
    }
}
