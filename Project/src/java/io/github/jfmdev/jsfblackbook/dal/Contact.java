package io.github.jfmdev.jsfblackbook.dal;

import java.io.Serializable;

/**
 * Bean class used for represent a rows in the 'contact' table.
 * 
 * @author jfmdev
 */
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;

    // ----- Fields ----- //
    
    private String fullName;
    private String address;
    private String telephone;
    private String email;
    private Integer relative;
    private Integer id;
    private Integer userId;
    // TODO: Add rating field.
    // TODO: Replace 'relative' by 'single'.
    
    // ----- Constructors ----- //
    
    /**
     * The default constructor.
     */
    public Contact() {
    }

    /**
     * Class constructor.
     * 
     * @param id A unique identifier.
     */
    public Contact(Integer id) {
        this.id = id;
    }

    /**
     * Class constructor.
     * 
     * @param id A unique identifier
     * @param fullName The name of the contact.
     */
    public Contact(Integer id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    // ----- Getters and setters ----- //
    
    /**
     * Gets the contact's full name.
     * 
     * @return A name.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the contact's full name.
     * 
     * @param fullName A name.
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Get the contact's address.
     * 
     * @return An address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the contact's address.
     * 
     * @param address An address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the contact's telephone.
     * 
     * @return A telephone.
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Sets the contact's telephone.
     * 
     * @param telephone A telephone.
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * Get the contact's e-mail.
     * 
     * @return An e-mail.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the contact's e-mail.
     * 
     * @param email An e-mail.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the relative's flag.
     * 
     * @return '1' if the contact is a relative, '0' otherwise.
     */
    public Integer getRelative() {
        return relative;
    }

    /**
     * Verifies if the contact is relative of the user.
     * 
     * @return 'true' if the contact is a relative, 'false' otherwise.
     */
    public boolean isRelative() {
        return relative == 1;
    }

    /**
     * Sets the relative flag.
     * 
     * @param relative '1' if the contact is a relative, '0' otherwise.
     */
    public void setRelative(Integer relative) {
        this.relative = relative;
    }

    /**
     * Sets the relative flag.
     * 
     * @param relative '1' if the contact is a relative, '0' otherwise.
     */
    public void setRelative(Boolean relative) {
        this.relative = relative == null? null : (relative? 1 : 0);
    }

    /**
     * Gets the contact's unique id.
     * 
     * @return An id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the contact's unique id.
     * 
     * @param id An id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the user's id.
     * 
     * @return An id.
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Set the user's id.
     * 
     * @param userId An id.
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return this.id + ": " + this.fullName;
    }
}
