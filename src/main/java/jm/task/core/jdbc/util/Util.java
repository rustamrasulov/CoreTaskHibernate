package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String dbHost = "localhost";
    private static final String dbtype = "mysql";
    private static final String dbPort = "3306";
    private static final String dbSchema = "taskjdbc";
    private static final String user = "TaskJDBC";
    private static final String password = "TaskJDBC";
    private static final String url = "jdbc:" + dbtype + "://" + dbHost + ":" + dbPort + "/" + dbSchema;

    private static Connection connection = null;
    private static jm.task.core.jdbc.util.Util instance = null;

    private Util() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, user, password);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static jm.task.core.jdbc.util.Util getInstance() {
        if (instance == null) {
            instance = new jm.task.core.jdbc.util.Util();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

}

