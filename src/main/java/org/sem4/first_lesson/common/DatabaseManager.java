package org.sem4.first_lesson.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/11/07
 */

public class DatabaseManager {

    private static final Logger logger = Logger.getLogger(DatabaseManager.class.getName());
    private static final Properties properties = new Properties();

    private static String driver;
    private static String username;
    private static String password;
    private static String databaseUri;

    private Connection conn;

    static {
        try (InputStream input = DatabaseManager.class.getClassLoader().getResourceAsStream("/config.properties")) {
            if (input == null) {
                throw new FileNotFoundException("Property file not found");
            }
            properties.load(input);

            driver = properties.getProperty("db.driver");
            username = properties.getProperty("db.username");
            password = properties.getProperty("db.password");
            databaseUri = properties.getProperty("db.uri");

        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error loading property file", ex);
        }
    }

    private DatabaseManager() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(databaseUri, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            logger.log(Level.WARNING, "Error while connecting to database: {0}", e.getMessage());
        }
    }

    public static DatabaseManager getInstance() {
        return DatabaseFactoryInstanceHolder.INSTANCE;
    }

    public Statement getStatement() {
        try {
            return conn.createStatement();
        } catch (SQLException e) {
            logger.warning(e.getMessage());
        }
        return null;
    }

    public PreparedStatement getPreparedStatement(String sql) {
        try {
            return conn.prepareStatement(sql);
        } catch (SQLException e) {
            logger.warning(e.getMessage());
        }
        return null;
    }

    private static class DatabaseFactoryInstanceHolder {
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }


}
