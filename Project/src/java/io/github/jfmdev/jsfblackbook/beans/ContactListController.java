package io.github.jfmdev.jsfblackbook.beans;

import io.github.jfmdev.jsfblackbook.dal.Contact;
import io.github.jfmdev.jsfblackbook.dal.ContactsDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.pmw.tinylog.Logger;

/**
 * Bean for the contact list view.
 * 
 * @author jfmdev
 */
@ManagedBean
@RequestScoped
public class ContactListController {
    
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
     * An integer indicating if the controller must display all the contacts or only the user's contacts.
     */
    private Integer all;

    /**
     * Gets an integer indicating if the controller is displaying all the contacts or only the user's contact.
     * 
     * @return A integer between 0 and 1.
     */
    public Integer getAll() {
        return all;
    }

    /**
     * Sets an integer indicating if the controller is displaying all the contacts or only the user's contact.
     * 
     * @param all A integer between 0 and 1.
     */
    public void setAll(Integer all) {
        this.all = all;
    }
    
    /**
     * Get the list of contacts.
     * 
     * @return A list of contacts.
     */
    public List<Contact> listContacts() {
        if(sessionData != null && sessionData.isLogged()) {
            try {
                if(all != null && all == 1 && sessionData.isAdmin()) {
                    return ContactsDAO.list();
                } else {                  ;
                    return ContactsDAO.list(sessionData.getUser().getId());
                }
            }catch(SQLException e) {
                Logger.error(e);
            }
        }
        return new ArrayList();
    }    
}
