package io.github.jfmdev.jsfblackbook.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Util class for using the database.
 * 
 * @author jfmdev
 */
public class MyDb {

    /**
     * Public constant for store the DB location.
     */
    public static final String DB_URL = "/WEB-INF/address_book.db";    
    
    /**
     * Variable for store the DB full path.
     */
    private static String DB_FULL_PATH = null;
    
    /**
     * Sets the full path of the database. This method can be invoked only once.
     * 
     * @param path A path.
     */
    public static void setDbFullPath(String path) {
        if(DB_FULL_PATH == null) DB_FULL_PATH = path;
    }
    
    /**
     * Gets the full path of the database file.
     * 
     * @return A path.
     */
    public static String getDbFullPath() {
        return DB_FULL_PATH;
    }
    
    /**
     * Gets a connectio to the database.
     * 
     * @return A connection to the database.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + DB_FULL_PATH);
    }
}
