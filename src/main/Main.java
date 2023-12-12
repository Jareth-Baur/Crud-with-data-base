/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 * @dateCreated December 8, 2023
 * @author Jareth Baur
 */
import java.util.Scanner;

public class Main {

    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StudentDatabase studentDatabase = new StudentDatabase();

        while (true) {
            System.out.println("1. Create");
            System.out.println("2. Read");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Exit");
            System.out.print("Select operation: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 ->
                    createRecord(scanner, studentDatabase);
                case 2 ->
                    studentDatabase.readRecords();
                case 3 ->
                    updateRecord(scanner, studentDatabase);
                case 4 ->
                    deleteRecord(scanner, studentDatabase);
                case 5 -> {
                    System.out.println("Exiting program.");
                    System.exit(0);
                }
                default ->
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    @SuppressWarnings("static-access")
    private static void createRecord(Scanner scanner, StudentDatabase studentDatabase) {
        System.out.print("Enter firstname: ");
        String firstname = scanner.nextLine();

        System.out.print("Enter lastname: ");
        String lastname = scanner.nextLine();

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        Student student = new Student(0, firstname, lastname, address);
        studentDatabase.createRecord(student);
    }

    @SuppressWarnings("static-access")
    private static void updateRecord(Scanner scanner, StudentDatabase studentDatabase) {
        System.out.print("Enter ID where to update student: ");
        int id = scanner.nextInt();

        scanner.nextLine();

        System.out.print("Enter new firstname: ");
        String firstname = scanner.nextLine();

        System.out.print("Enter new lastname: ");
        String lastname = scanner.nextLine();

        System.out.print("Enter new address: ");
        String address = scanner.nextLine();

        Student student = new Student(id, firstname, lastname, address);
        studentDatabase.updateRecord(student);
    }

    @SuppressWarnings("static-access")
    private static void deleteRecord(Scanner scanner, StudentDatabase studentDatabase) {
        System.out.print("Enter record ID to delete: ");
        int id = scanner.nextInt();
        studentDatabase.deleteRecord(id);
    }
}
