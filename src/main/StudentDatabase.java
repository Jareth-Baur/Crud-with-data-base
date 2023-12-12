/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 * @dateCreated December 8, 2023
 * @author Jareth Baur
 */
import java.sql.*;

public class StudentDatabase {

    private static final String URL = "jdbc:mysql://localhost:3306/oop_jdbc";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
            throw new RuntimeException("Database connection error", e);
        }
    }

    public static void createRecord(Student student) {
        String insertSQL = "INSERT INTO students (firstname, lastname, address) VALUES (?, ?, ?)";
        try (Connection connection = connect(); PreparedStatement statement = connection.prepareStatement(insertSQL)) {
            statement.setString(1, student.getFirstname());
            statement.setString(2, student.getLastname());
            statement.setString(3, student.getAddress());
            statement.executeUpdate();
            System.out.println("Record created successfully.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void readRecords() {
        String selectSQL = "SELECT * FROM students";
        try (Connection connection = connect(); PreparedStatement statement = connection.prepareStatement(selectSQL); ResultSet resultSet = statement.executeQuery()) {
            String line = "------------------------------------------------------";
            System.out.printf("%-5s%-15s%-15s%-15s%n", "ID", "Firstname", "Lastname", "Address");
            System.out.println(line);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String address = resultSet.getString("address");
                Student student = new Student(id, firstname, lastname, address);
                System.out.println(student);
            }
            System.out.println(line);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void updateRecord(Student student) {
        String updateSQL = "UPDATE students SET firstname=?, lastname=?, address=? WHERE id=?";
        try (Connection connection = connect(); PreparedStatement statement = connection.prepareStatement(updateSQL)) {
            statement.setString(1, student.getFirstname());
            statement.setString(2, student.getLastname());
            statement.setString(3, student.getAddress());
            statement.setInt(4, student.getId());
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Record updated successfully.");
            } else {
                System.out.println("No record found with ID: " + student.getId());
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void deleteRecord(int id) {
        String deleteSQL = "DELETE FROM students WHERE id=?";
        try (Connection connection = connect(); PreparedStatement statement = connection.prepareStatement(deleteSQL)) {
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Record deleted successfully.");
            } else {
                System.out.println("No record found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
