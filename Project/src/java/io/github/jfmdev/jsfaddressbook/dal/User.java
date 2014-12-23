package io.github.jfmdev.jsfaddressbook.dal;

import java.io.Serializable;

/**
 * Bean class used for represent a rows in the 'user' table.
 * 
 * @author jfmdev
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    // ----- Fields ----- //
    
    private String name;
    private String pass;
    private Integer isAdmin;
    private Integer id;

    // ----- Constructors ----- //
    
    /**
     * Default constructor.
     */
    public User() {
    }

    /**
     * Class constructor.
     * 
     * @param id An unique id.
     */
    public User(Integer id) {
        this.id = id;
    }

    /**
     * Class constructor.
     * 
     * @param id An unique id.
     * @param name A name.
     * @param pass A password hashed with SHA1.
     */
    public User(Integer id, String name, String pass) {
        this.id = id;
        this.name = name;
        this.pass = pass;
    }

    // ----- Getters and setters ----- //
    
    /**
     * Gets the user's name.
     * 
     * @return A name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's name.
     * 
     * @param name A name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the user's hashed password.
     * 
     * @return A hashed password.
     */
    public String getPass() {
        return pass;
    }

    /**
     * Set the user's hashed password.
     * 
     * @param pass A hashed password.
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * Gets the 'is_admin' flag.
     * 
     * @return '1' if the user is an administrator, '0' othewise.
     */
    public Integer getIsAdmin() {
        return isAdmin;
    }

    /**
     * Gets the 'is_admin' flag.
     * 
     * @return 'true' if the user is an administrator, 'false' othewise.
     */
    public boolean isAdmin() {
        return isAdmin == 1;
    }

    /**
     * Sets the 'is_admin' flag.
     * 
     * @param isAdmin '1' if the user is an administrator, '0' othewise.
     */
    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * Sets the 'is_admin' flag.
     * 
     * @param isAdmin 'true' if the user is an administrator, 'false' othewise.
     */
    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin == null? null : (isAdmin? 1 : 0);
    }

    /**
     * Gets the user's unique id.
     * 
     * @return An unique id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the user's unique id.
     * 
     * @param id An unique id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.id + ": " + this.name;
    }
    
}
