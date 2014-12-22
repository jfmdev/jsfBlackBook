package io.github.jfmdev.jsfaddressbook.dal;

import java.io.Serializable;

/**
 *
 * @author jfmdev
 */
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fullName;
    private String address;
    private String telephone;
    private String email;
    private Integer relative;
    private Integer id;
    private Integer userId;

    public Contact() {
    }

    public Contact(Integer id) {
        this.id = id;
    }

    public Contact(Integer id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRelative() {
        return relative;
    }

    public void setRelative(Integer relative) {
        this.relative = relative;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return this.id + ": " + this.fullName;
    }
}
