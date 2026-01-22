package com.tictactoe;

import java.sql.*;

/**
 * Java 8 program to connect to Oracle database and display all records from emp table
 */
public class EmpTableDisplay {
    
    // Database connection details
    private static final String DB_URL = "jdbc:oracle:thin:@192.168.0.14:1521/asmacsdb";
    private static final String USERNAME = "emp";
    private static final String PASSWORD = "emp";
    
    /**
     * Helper method to repeat a string (Java 8 compatible)
     */
    private static String repeatString(String str, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            // Load Oracle JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            // Create connection
            System.out.println("Connecting to Oracle database...");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connection established successfully!\n");
            
            // Create statement
            statement = connection.createStatement();
            
            // Execute query to select all records from emp table
            String query = "SELECT * FROM emp";
            System.out.println("Executing query: " + query);
            resultSet = statement.executeQuery(query);
            
            // Get metadata to determine column count and names
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            // Display column headers
            System.out.println("\n" + repeatString("=", 80));
            System.out.print("|");
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                System.out.printf(" %-15s |", columnName);
            }
            System.out.println();
            System.out.println(repeatString("=", 80));
            
            // Display records
            int recordCount = 0;
            while (resultSet.next()) {
                recordCount++;
                System.out.print("|");
                for (int i = 1; i <= columnCount; i++) {
                    Object value = resultSet.getObject(i);
                    String displayValue = (value == null) ? "NULL" : value.toString();
                    // Truncate if too long
                    if (displayValue.length() > 15) {
                        displayValue = displayValue.substring(0, 12) + "...";
                    }
                    System.out.printf(" %-15s |", displayValue);
                }
                System.out.println();
            }
            System.out.println(repeatString("=", 80));
            System.out.println("\nTotal records retrieved: " + recordCount);
            
        } catch (ClassNotFoundException e) {
            System.err.println("Oracle JDBC Driver not found!");
            System.err.println("Please ensure ojdbc8.jar is in the classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database connection error:");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred:");
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                    System.out.println("\nDatabase connection closed.");
                }
            } catch (SQLException e) {
                System.err.println("Error closing resources:");
                e.printStackTrace();
            }
        }
    }
}
