package services;

import java.sql.*;
import java.util.*;

import models.*;

public class StudentService {

    public void studentModule(Scanner scanner, Connection con){

        while(true){
            System.out.print(
                "\n-- Student Module --\n" +
                "[1] Add New Student\n" +
                "[2] View All Students\n" +
                "[3] Edit Student Info\n" +
                "[4] Delete Student\n" +
                "[5] Back\n" +
                "Enter your choice: "
            );

            String userChoice = scanner.nextLine();
            switch (userChoice) {
                case "1" -> addStudent(scanner, con);
                case "2" -> viewAllStudent(con);
                case "3" -> editStudentInfo(scanner, con);
                case "4" -> deleteStudent(scanner, con);
                case "5" -> { // back
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }

    }

    public void addStudent(Scanner scanner, Connection con){

        System.out.print("Enter Student Full Name: ");
        String fullName = scanner.nextLine();

        System.out.print("Enter Student Email Name: ");
        String email = scanner.nextLine();

        System.out.print("Enter Student Age Name: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        Student student = new Student(fullName, email, age);

        try {

            PreparedStatement ps = con.prepareStatement("INSERT INTO Student(FullName, Email, Age) VALUES (?, ?, ?);");
            ps.setString(1, student.getFullName());
            ps.setString(2, student.getEmail());
            ps.setInt(3, student.getAge());
            ps.executeUpdate();
            System.out.println("Student has been added successfully.");
                        
            } catch (SQLException e) {
            e.getStackTrace();
        }

    }

    public void viewAllStudent(Connection con){

        try {
            
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Student;");
            ResultSet rs = ps.executeQuery();

            System.out.println();
            while(rs.next()){
                System.out.println(
                    "Student ID [" + rs.getInt(1) + "]" + " | " +
                    "Full Name: " + rs.getString(2) + " | " +
                    "Email: " + rs.getString(3) + " | " +
                    "Age: " + rs.getInt(4) + " | "
                );
            }

        } catch (SQLException e) {
            e.getStackTrace();
        }

    }

    public void editStudentInfo(Scanner scanner, Connection con){
        
        try {
            
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Student;");
            ResultSet rs = ps.executeQuery();

            System.out.println();
            while(rs.next()){
                System.out.println(
                    "Student ID [" + rs.getInt(1) + "]" + " | " +
                    "Full Name: " + rs.getString(2) + " | " +
                    "Email: " + rs.getString(3) + " | " +
                    "Age: " + rs.getInt(4) + " | "
                );
            }

            System.out.print("\nSelect the student ID to edit: ");
            int userInputIDToDelete = scanner.nextInt();
            scanner.nextLine();

            PreparedStatement ps1 = con.prepareStatement("SELECT FullName, Email, Age FROM Student WHERE StudentID = ?;");
            ps1.setInt(1, userInputIDToDelete);
            ResultSet rs1 = ps1.executeQuery();
            while(rs1.next()){
                System.out.println(
                    "Full Name: " + rs1.getString(1) + " | " +
                    "Email: " + rs1.getString(2) + " | " +
                    "Age: " + rs1.getInt(3) + " | "
                );
            }

            System.out.print("Enter new Full Name: ");
            String newFullName = scanner.nextLine();

            System.out.print("Enter new Email: ");
            String newEmail = scanner.nextLine();

            System.out.print("Enter new Age: ");
            int newAge = scanner.nextInt();
            scanner.nextLine();

            Student student = new Student(newFullName, newEmail, newAge);
            PreparedStatement ps2 = con.prepareStatement("UPDATE Student SET FullName = ?, Email = ?, Age = ? WHERE StudentID = ?;");
            ps2.setString(1, student.getFullName());
            ps2.setString(2, student.getEmail());
            ps2.setInt(3, student.getAge());
            ps2.setInt(4, userInputIDToDelete);
            ps2.executeUpdate();
            System.out.println("Student information has been successfully updated.");

        } catch (SQLException e) {
            e.getStackTrace();
        }

    }

    public void deleteStudent(Scanner scanner, Connection con){

        try {
            
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Student;");
            ResultSet rs = ps.executeQuery();
            System.out.println();
            while(rs.next()){
                System.out.println(
                    "Student ID [" + rs.getInt(1) + "]" + " | " +
                    "Full Name: " + rs.getString(2) + " | " +
                    "Email: " + rs.getString(3) + " | " +
                    "Age: " + rs.getInt(4) + " | "
                );
            }

            System.out.print("Enter Student ID to delete: ");
            String studentIDToDelete = scanner.nextLine();

            PreparedStatement ps1 = con.prepareStatement("DELETE FROM Enrollment WHERE StudentID = ?; DELETE FROM Student WHERE StudentID = ?;");
            ps1.setInt(1, Integer.parseInt(studentIDToDelete));
            ps1.setInt(2, Integer.parseInt(studentIDToDelete));
            ps1.executeUpdate();
            System.out.println("The student has been successfully deleted");

        } catch (SQLException e) {
            e.getStackTrace();
        }

    }

}
