package org.comcast.crm.generic.databaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class DataBaseUtil {
    private Connection conn;

    public void getDBConnection(String url, String username, String password) {
        try {
            Driver driver = new Driver();
        } catch (SQLException e) {
            System.out.println("Could not connect to data base " + e.getMessage());
        }
    }

    public void closeConnection() throws SQLException {
        conn.close();
    }


    public ResultSet executeSelectQuery(String query) {
        ResultSet res = null;

        try {
            res = conn.createStatement().executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return res;
    }

    public int executeNonSelectQuery(String query) {
        int res = 0;
        try {
            res = conn.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return res;
    }

}
