package io.github.jfmdev.jsfaddressbook.dal;

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
        List<Contact> contacts = (List<Contact>) runner.query(conn, "SELECT * FROM contact WHERE user_id = ?", new BeanListHandler(Contact.class), userId);
        DbUtils.close(conn);
        return contacts;
    }    
}
