package io.github.jfmdev.jsfaddressbook.beans;

import io.github.jfmdev.jsfaddressbook.dal.User;
import io.github.jfmdev.jsfaddressbook.dal.UsersDAO;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.pmw.tinylog.Logger;

/**
 * Bean for the login form.
 * 
 * @author jfmdev
 */
@ManagedBean
@RequestScoped
public class LoginController {

    /**
     * The user's name.
     */
    private String username;
    
    /**
     * The user's password.
     */
    private String password;
    
    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
        this.username = null;
        this.password = null;
    }
    
    /**
     * Tries to login the user.
     * 
     * @return 'true' if user is logged, 'false' if contrary.
     */
    public boolean login() {
Logger.info("hola");        
        try {
            // Verify that username is not empty.
            if(this.username == null || this.username.isEmpty()) {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "The username can not be empty", null);
                FacesContext.getCurrentInstance().addMessage(null, facesMsg);
                return false;
            }

            // Verify that the username and password are valid.
            User currentUser = UsersDAO.find(username, UsersDAO.toSHA1(password));
            if(currentUser == null) {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid username or password", null);
                FacesContext.getCurrentInstance().addMessage(null, facesMsg);
                return false;
            }

            return true;
        }catch(SQLException e) {
            Logger.error(e);
        }
        
        return false;
    }
    
    /**
     * Set the username.
     * 
     * @param username A string.
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * Returns the user's name.
     * 
     * @return The name of the user.
     */
    public String getUsername() {
        return this.username;
    }    
    
    /**
     * Set the password.
     * 
     * @param password A string.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Returns the user's password.
     * 
     * @return The password of the user.
     */
    public String getPassword() {
        return this.password;
    }
}
