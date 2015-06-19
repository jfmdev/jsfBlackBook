package io.github.jfmdev.jsfblackbook.beans;

import io.github.jfmdev.jsfblackbook.dal.User;
import io.github.jfmdev.jsfblackbook.dal.UsersDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.pmw.tinylog.Logger;

/**
 * Bean for the user list view.
 * 
 * @author jfmdev
 */
@ManagedBean
@RequestScoped
public class UserListController {
    /**
     * Get the list of users.
     * 
     * @return A list of users.
     */
    public List<User> listUsers() {
        try {
            return UsersDAO.list();
        }catch(SQLException e) {
            Logger.error(e);
        }
        return new ArrayList();
    }
}
