package org.theresilient.dev.connection;

import org.theresilient.dev.shorcuts.CMD;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SQLQuery {

    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite::memory:");
            Statement statement = connection.createStatement();

            statement.executeUpdate("DROP TABLE IF EXISTS users");
            statement.executeUpdate("CREATE TABLE users(id, username, password)");
            statement.executeUpdate("INSERT INTO users values(1, \"amah\", \"kwatcha\")");
            statement.executeUpdate("INSERT INTO users values(2, \"abalo\", \"afi\")");
        } catch (SQLException e) {
            System.out.println("Error opening database: " + e.getMessage());
        }
    }

    public static List<HashMap<String, Object>> getUser(String username) {
        var data = new ArrayList<HashMap<String, Object>>();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users WHERE username = '" + username + "'";
            CMD.printLn("Query: " + query);
            ResultSet results = statement.executeQuery(query);


            if (results.next()) {
                do {
                    var user = new HashMap<String, Object>();
                    user.put("id", results.getString("id"));
                    user.put("username", results.getString("username"));
                    user.put("password", results.getString("password"));

                    data.add(user);
                } while (results.next());
            }
        } catch (Exception e) {
//            System.out.println("Error executing query on database: " + e.getMessage());
        }

        return data;
    }

    public static HashMap<String, Object> logIn(String username, String password) {
        var user = new HashMap<String, Object>();

        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
            CMD.printLn("Query: " + query);
            ResultSet results = statement.executeQuery(query);


            if (results.next()) {
                user.put("id", results.getString("id"));
                user.put("username", results.getString("username"));
                user.put("password", results.getString("password"));
            }
        } catch (Exception e) {
//            System.out.println("Error executing query on database: " + e.getMessage());
        }

        return user;
    }

    public static List<HashMap<String, Object>> getUserSafe(String username) {
        var data = new ArrayList<HashMap<String, Object>>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");

            statement.setString(1, username);
            statement.execute();

            ResultSet results = statement.getResultSet();

            if (results.next()) {
                do {
                    var user = new HashMap<String, Object>();
                    user.put("id", results.getString("id"));
                    user.put("username", results.getString("username"));
                    user.put("password", results.getString("password"));

                    data.add(user);
                } while (results.next());
            }
        } catch (Exception e) {
            System.out.println("Error executing query on database: " + e.getMessage());
        }

        return data;
    }

    public static HashMap<String, Object> logInSafe(String username, String password) {
        var user = new HashMap<String, Object>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");

            statement.setString(1, username);
            statement.setString(2, password);
            statement.execute();

            ResultSet results = statement.getResultSet();

            if (results.next()) {
                user.put("id", results.getString("id"));
                user.put("username", results.getString("username"));
                user.put("password", results.getString("password"));

            }
        } catch (Exception e) {
            System.out.println("Error executing query on database: " + e.getMessage());
        }

        return user;
    }
}
