package io.github.jfmdev.jsfblackbook.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Bean for the user form.
 * 
 * @author jfmdev
 */
@ManagedBean
@RequestScoped
public class UserFormController {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    // TODO: Verify that the user is editing himself or is admin.
}
