package io.github.jfmdev.jsfblackbook.beans;

import io.github.jfmdev.jsfblackbook.dal.User;
import io.github.jfmdev.jsfblackbook.dal.UsersDAO;
import java.sql.SQLException;
import java.util.Objects;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.pmw.tinylog.Logger;

/**
 * Bean for the user form.
 * 
 * @author jfmdev
 */
@ManagedBean
@RequestScoped
public class UserFormController {
    /**
     * The user to edit.
     */
    private User user;
    
    /**
     * The new password of the user.
     */
    private String newPass;
    
    /**
     * Default constructor.
     */
    public UserFormController() {
        this.user = new User();
        this.newPass = "";
    }
    
    /**
     * The object for store the session data.
     */
    @ManagedProperty(value="#{sessionData}")
    private SessionData sessionData;
    
    /**
     * Sets the session data.
     * 
     * @param data The session data.
     */
    public void setSessionData(SessionData data) {
        this.sessionData = data;
    }

    /**
     * Gets the user's id.
     * 
     * @return The user's id.
     */
    public Integer getId() {
        return user.getId();
    }

    /**
     * Sets the user's id and load the user's data from the database.
     * 
     * @param id The user's id.
     * @throws SQLException 
     */
    public void setId(Integer id) throws SQLException {
        // Check if the user is an administrator or if is editing himself.
        if(sessionData.isAdmin() || (id != null && id.equals(sessionData.getUserId()))) {
            // Verify if the user is going to be created or edited.
            if(id != null && id >= 0) {
                // Find the user's data.
                user = UsersDAO.find(id);
            } 
        }
    }
    
    /**
     * Sets the user's name.
     * 
     * @param username The user's name.
     */
    public void setUsername(String username) {
        user.setName(username);
    }
    
    /**
     * Gets the user's name.
     * 
     * @return The user's name.
     */
    public String getUsername() {
        return user.getName();
    }
    
    /**
     * Sets the user's password.
     * 
     * @param password The user's password.
     */
    public void setPassword(String password) {
        newPass = password;
    }
    
    /**
     * Gets the user's password.
     * 
     * @return The user's password.
     */
    public String getPassword() {
        return newPass;
    }
    
    /**
     * Sets if the user is an administrator.
     * 
     * @param isAdmin 'true' if the user is admin, 'false' otherwise.
     */
    public void setAdmin(Boolean isAdmin) {
        user.setIsAdmin(isAdmin);
    }
    
    /**
     * Checks if the user is an administrator.
     * 
     * @return 'true' if the user is admin, 'false' otherwise.
     */
    public Boolean getAdmin() {
        return user.isAdmin();
    }
    
    /**
     * Deletes the user.
     * 
     * @return 'true' if the user was deleted, 'false' otherwise.
     */
    public Boolean delete() {
        try {    
            if(user.getId() != null) {
                UsersDAO.delete(user.getId());
                return true;
            }
        }catch(SQLException e) {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
            Logger.error(e);
        }
        
        return null;
    }
    
    /**
     * Saves the user.
     * 
     * @return 'true' if the user was saved, 'false' otherwise.
     */
    public Boolean save() {
        try {
            // Verify that username is not empty.
            if(user.getName() == null || user.getName().isEmpty()) {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "The username can not be empty", null);
                FacesContext.getCurrentInstance().addMessage(null, facesMsg);          
                return null;
            }

            // Verify that the password is not empty if the user is being created.
            if((user.getId() == null || user.getId() < 0) && (newPass == null || newPass.isEmpty())) {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "The password can not be empty", null);
                FacesContext.getCurrentInstance().addMessage(null, facesMsg);
                return null;
            }
            
            // Verify if the password was modified.
            if(newPass != null && !newPass.isEmpty()) {
                user.setPass(UsersDAO.toSHA1(newPass));
            }

            // Verify if the user has permission to do the update.
            if(sessionData.isAdmin() || Objects.equals(user.getId(), sessionData.getUserId())) {
                // Save user.
                UsersDAO.save(user);
                
                // Show success message.
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "The changes were saved", null);
                FacesContext.getCurrentInstance().addMessage(null, facesMsg);
            }

            // If the user was being created, return to the list page.
            return user == null || user.getId() == null? true : null;
        }catch(SQLException e) {
            // Show error message.
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
            Logger.error(e);
            return null;
        }
    }
}
