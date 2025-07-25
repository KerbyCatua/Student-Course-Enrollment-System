package services;

import java.sql.*;
import java.util.*;

import javax.naming.spi.DirStateFactory.Result;

public class EnrollmentService {

    public void enrollmentModule(Scanner scanner, Connection con){

        while(true){

            System.out.print(
                "\n-- Enrollment Module --\n" +
                "[1] Enroll a Student in a Course\n" +
                "[2] View All Enrollments\n" +
                "[3] View Students Enrolled in a Specific Course\n" +
                "[4] View Courses a Student is Enrolled In\n" +
                "[5] Back\n" +
                "Enter your choice: "
            );

            String userChoice = scanner.nextLine();

            switch (userChoice) {
                case "1" -> enrollStudent(scanner, con);
                case "2" -> System.out.println(); // TODO View All Enrollments
                case "3" -> System.out.println(); // TODO View Students Enrolled in a Specific Course
                case "4" -> System.out.println(); // TODO View Courses a Student is Enrolled In
                case "5" -> { // back
                    return;
                }
                default -> System.out.println("Invalid choice");
            }

        }
        
    }

    public void enrollStudent(Scanner scanner, Connection con){
        
        try {
            
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Enrollment;");
            ResultSet rs = ps.executeQuery();
            System.out.println("\nStudent(s) Enrolled");
            while(rs.next()){
                System.out.println(
                    "-- Enrollment ID [" + rs.getInt("EnrollmentID") + "] | " + 
                    "Student ID: " + rs.getInt("StudentID") + " | " + 
                    "Course ID: " + rs.getInt("CourseID") + " | " + 
                    "Enrollment Date: " + rs.getDate("EnrollDate") + " | "
                );
            }

            System.out.print("\nSelect Student ID to Enroll: ");
            String studentIDToEnroll = scanner.nextLine();
            
            System.out.print("Select CourseID to Enroll: ");
            String courseIDToEnroll = scanner.nextLine();

            PreparedStatement ps1 = con.prepareStatement("SELECT FullName, CourseName FROM Student, Course WHERE StudentID = ? AND CourseID = ?;");
            ps1.setInt(1, Integer.parseInt(studentIDToEnroll));
            ps1.setInt(2, Integer.parseInt(courseIDToEnroll));
            ResultSet rs1 = ps1.executeQuery();
            while(rs1.next()){
                System.out.println(
                    "\nStudent FullName: " + rs1.getString("FullName") + 
                    "\nStudent Course: " + rs1.getString("CourseName")
                );
            }

            System.out.print("Do you confirm the enrollment of this student? (Yes/No): ");
            String yesOrNoChoice = scanner.nextLine();

            switch (yesOrNoChoice) {
                case "Yes" -> {
                    
                    PreparedStatement ps2 = con.prepareStatement("INSERT INTO Enrollment(StudentID, CourseID, EnrollDate) VALUES (?, ?, GETDATE());");
                    ps2.setInt(1, Integer.parseInt(studentIDToEnroll));
                    ps2.setInt(2, Integer.parseInt(courseIDToEnroll));
                    ps2.executeUpdate();
                    System.out.println("Enrollment Successful.");
                    
                }
                case "No" -> System.out.println("Enrollment Canceled.");
                default -> System.out.println("Invalid Choice, enrollment Canceled.");
            }

        } catch (SQLException e) {
            e.getStackTrace();
        }

    }

}
