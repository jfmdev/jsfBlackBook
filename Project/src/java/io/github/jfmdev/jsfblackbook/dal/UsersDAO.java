package io.github.jfmdev.jsfblackbook.dal;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.pmw.tinylog.Logger;

/**
 * DAO layer for the user table.
 * 
 * @author jfmdev
 */
public class UsersDAO {
    /**
     * Find a user by his id.
     * 
     * @param id The user's id.
     * @return An user or 'null' if there is not any user with that id.
     * @throws SQLException
     */
    public static User find(int id) throws SQLException {
        Connection conn = MyDb.getConnection();
        QueryRunner runner = new QueryRunner();
        User user = (User) runner.query(conn, "SELECT * FROM user WHERE id = ?", new BeanHandler(User.class), id);        
        DbUtils.close(conn);
        return user;
    }
    
    /**
     * Find a user by his name and password.
     * 
     * @param name The user's name.
     * @param password The user's password.
     * @return An user or 'null' if there is not any user with that name and password.
     * @throws SQLException
     */
    public static User find(String name, String password) throws SQLException {
        Connection conn = MyDb.getConnection();
        QueryRunner runner = new QueryRunner();
        User user = (User) runner.query(conn, "SELECT * FROM user WHERE name = ? AND pass = ?", new BeanHandler(User.class), name, password);
        DbUtils.close(conn);
        return user;
    }
    
    /**
     * List all users.
     * 
     * @return A list of users.
     * @throws SQLException
     */
    public static List<User> list() throws SQLException {
        Connection conn = MyDb.getConnection();
        QueryRunner runner = new QueryRunner();
        List<User> users = (List<User>) runner.query(conn, "SELECT * FROM user", new BeanListHandler(User.class));
        DbUtils.close(conn);
        return users;
    }
    
    /**
     * Calculates the SHA-1 hash for a string.
     * 
     * @param text A string.
     * @return A string with a SHA-1 hash.
     */
    public static String toSHA1(String text) {
        return text != null? toSHA1(text.getBytes()) : null;
    }
    
    /**
     * Calculates the SHA-1 hash for an array of bytes.
     * 
     * @param array An arrays of bytes.
     * @return A string with a SHA-1 hash.
     */
    public static String toSHA1(byte[] array) {
        MessageDigest md = null;
        try { md = MessageDigest.getInstance("SHA-1"); } catch(NoSuchAlgorithmException e) { Logger.error(e); } 
        String res = array != null && md != null? toHex(md.digest(array)) : null;
        return res;
    }
    
    /**
     * Converts a byte array into an String with hexadecimal values.
     * 
     * @param bytes A byte array.
     * @return A string.
     */
    public static String toHex(byte[] bytes) {
        BigInteger bi = new BigInteger(1, bytes);
        return String.format("%0" + (bytes.length << 1) + "X", bi);
    }
}
