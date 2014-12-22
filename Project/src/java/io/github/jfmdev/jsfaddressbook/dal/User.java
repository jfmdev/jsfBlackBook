package io.github.jfmdev.jsfaddressbook.dal;

import java.io.Serializable;

/**
 *
 * @author jfmdev
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String pass;
    private Integer isAdmin;
    private Integer id;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String name, String pass) {
        this.id = id;
        this.name = name;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.id + ": " + this.name;
    }
    
}
