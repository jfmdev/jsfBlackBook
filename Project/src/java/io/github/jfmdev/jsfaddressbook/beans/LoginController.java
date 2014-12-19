package io.github.jfmdev.jsfaddressbook.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author jfmdev
 */
@ManagedBean
@SessionScoped
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
        // Verify that username is not empty.
        if(this.username == null || this.username.isEmpty()) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "The username can not be empty", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
            return false;
        }
        
        // Verify that the username and password are valid.
        if(this.password == null || this.username.compareTo(password) != 0) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid username or password", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
            return false;
        }
        
        return true;
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
