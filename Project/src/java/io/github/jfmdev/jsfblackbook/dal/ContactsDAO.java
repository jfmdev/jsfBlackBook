package io.github.jfmdev.jsfblackbook.dal;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 * DAO layer for the contact table.
 * 
 * @author jfmdev
 */
public class ContactsDAO {
    /**
     * Find a contact by his id.
     * 
     * @param id The contact's id.
     * @return An user or 'null' if there is not any user with that contact.
     * @throws SQLException
     */
    public static Contact find(int id) throws SQLException {
        Connection conn = MyDb.getConnection();
        QueryRunner runner = new QueryRunner();
        Contact contact = (Contact) runner.query(conn, "SELECT * FROM contact WHERE id = ?", new BeanHandler(Contact.class), id);
        DbUtils.close(conn);
        return contact;
    }
    
    /**
     * List all contacts.
     * 
     * @return A list of contacts.
     * @throws SQLException
     */
    public static List<Contact> list() throws SQLException {
        Connection conn = MyDb.getConnection();
        QueryRunner runner = new QueryRunner();
        List<Contact> contacts = (List<Contact>) runner.query(conn, "SELECT * FROM contact", new BeanListHandler(Contact.class));
        DbUtils.close(conn);
        return contacts;
    }    
    
    /**
     * Get all contacts from an user.
     * 
     * @param userId An user's id.
     * @return A list of contacts.
     * @throws SQLException
     */
    public static List<Contact> list(int userId) throws SQLException {
        Connection conn = MyDb.getConnection();
        QueryRunner runner = new QueryRunner();
        List<Contact> contacts = (List<Contact>) runner.query(conn, "SELECT * FROM contact WHERE userId = ?", new BeanListHandler(Contact.class), userId);
        DbUtils.close(conn);
        return contacts;
    }    
    
    /**
     * Deletes a contact.
     * 
     * @param id The contact's id.
     * @throws SQLException
     */
    public static void delete(int id) throws SQLException {
        Connection conn = MyDb.getConnection();
        QueryRunner runner = new QueryRunner();
        runner.update(conn, "DELETE FROM contact WHERE id = ?", id);        
    }
    
    /**
     * Saves a contact.
     * 
     * @param contact The contact's data.
     * @throws SQLException
     */
    public static void save(Contact contact) throws SQLException {
        if(contact != null) {
            Connection conn = MyDb.getConnection();
            QueryRunner runner = new QueryRunner();
            if(contact.getId() == null || contact.getId() < 0) {
                runner.update(conn, "INSERT INTO contact (fullName, address, telephone, email, single, userId, score) VALUES (?, ?, ?, ?, ?, ?, ?)", contact.getFullName(), contact.getAddress(), contact.getTelephone(), contact.getEmail(), contact.getSingle(), contact.getUserId(), contact.getScore());
            } else {
                runner.update(conn, "UPDATE contact SET fullName = ?, address = ?, telephone = ?, email = ?, single = ?, userId = ?, score = ? WHERE id = ?", contact.getFullName(), contact.getAddress(), contact.getTelephone(), contact.getEmail(), contact.getSingle(), contact.getUserId(), contact.getScore(), contact.getId());
            }
        }
    }    
}
