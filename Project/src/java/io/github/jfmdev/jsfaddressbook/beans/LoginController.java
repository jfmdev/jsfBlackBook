package io.github.jfmdev.jsfaddressbook.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
     * Creates a new instance of LoginController
     */
    public LoginController() {
        this.username = "Unknown";
    }
    
    /**
     * Returns the user's name.
     * 
     * @return The name of the user.
     */
    public String getUsername() {
        return this.username;
    }
}
