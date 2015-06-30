package io.github.jfmdev.jsfblackbook.beans;

import io.github.jfmdev.jsfblackbook.dal.Contact;
import io.github.jfmdev.jsfblackbook.dal.ContactsDAO;
import java.sql.SQLException;
import java.util.Objects;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.pmw.tinylog.Logger;

/**
 * Bean for the contact form.
 * 
 * @author jfmdev
 */
@ManagedBean
@RequestScoped
public class ContactFormController {
    /**
     * The contact to edit.
     */
    private Contact contact;

    /**
     * Default controller.
     */
    public ContactFormController() {
        this.contact = new Contact();
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
     * Gets the contact's id.
     * 
     * @return The contact's id.
     */
    public Integer getId() {
        return contact.getId();
    }

    /**
     * Sets the contact's id and load his data from the database.
     * 
     * @param id The contact's id.
     */
    public void setId(Integer id) throws SQLException {
        // Verify if the contact is going to be created or edited.
        if(id != null && id >= 0) {
            // Find the contact's data.
            Contact someContact = ContactsDAO.find(id);
            
            // Verify if the contact belongs to the user or if the user is admin.
            if(someContact != null && (sessionData.isAdmin() || Objects.equals(sessionData.getUserId(), someContact.getUserId()))) {
                contact = someContact;
            }
        }
    }

    /**
     * Get the contact's data.
     * 
     * @return The contact's data.
     */
    public Contact getData() {
        return contact;
    }
    
    /**
     * Deletes the contact.
     * 
     * @return 'true' if the contact was deleted, 'false' otherwise.
     */
    public Boolean delete() {
        try {    
            // Verify if the user has permission to do the update.
            if(contact.getId() != null && (sessionData.isAdmin() || Objects.equals(contact.getUserId(), sessionData.getUserId()))) {
                ContactsDAO.delete(contact.getId());
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
     * Saves the contact.
     * 
     * @return 'true' if the contact was saved, 'false' otherwise.
     */
    public Boolean save() {
        try {
            // Verify that the name is not empty.
            if(contact.getFullName() == null || contact.getFullName().isEmpty()) {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "The full name can not be empty", null);
                FacesContext.getCurrentInstance().addMessage(null, facesMsg);          
                return null;
            }

            // Verify if the user has permission to do the update.
            if(sessionData.isAdmin() || contact.getUserId() == null || Objects.equals(contact.getUserId(), sessionData.getUserId())) {
                // By default, the contact belongs to the user that is creating him.
                if(contact.getId() == null) contact.setUserId(sessionData.getUserId());
                
                // Save user.
                ContactsDAO.save(contact);
                
                // Show success message.
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "The changes were saved", null);
                FacesContext.getCurrentInstance().addMessage(null, facesMsg);
            }

            // If the user was being created, return to the list page.
            return contact == null || contact.getId() == null? true : null;
        }catch(SQLException e) {
            // Show error message.
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
            Logger.error(e);
            return null;
        }
    }    
}
