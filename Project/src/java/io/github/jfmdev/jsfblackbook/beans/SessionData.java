package io.github.jfmdev.jsfblackbook.beans;

import io.github.jfmdev.jsfblackbook.dal.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Bean for store all the information related to the session.
 * 
 * @author jfmdev
 */
@ManagedBean
@SessionScoped
public class SessionData {

    /**
     * Current user.
     */
    private User user;
    
    /**
     * Creates a new instance of SessionData
     */
    public SessionData() {
        this.user = null;
    }

    /**
     * Get the user.
     * 
     * @return The user data.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user data.
     * 
     * @param user The user's data.
     */
    public void setUser(User user) {
        this.user = user;
    }
    
    /**
     * Checks if the user is logged.
     * 
     * @return 'true' if the user is logged, 'false' if not.
     */
    public boolean isLogged() {
        return this.user != null;
    }
    
    /**
     * Verifies if the user is logged and has admin privileges.
     * 
     * @return 'true' if the user has admin privileges, 'false' if not.
     */
    public boolean isAdmin() {
        return this.user != null && this.user.isAdmin();
    }
}
