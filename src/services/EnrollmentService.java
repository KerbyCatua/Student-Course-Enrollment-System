package services;

import java.sql.*;
import java.util.*;


public class EnrollmentService {

    public void enrollmentModule(Scanner scanner, Connection con){

        while(true){

            System.out.print(
                "\n-- Enrollment Module --\n" +
                "[1] Enroll a Student in a Course\n" +
                "[2] View All Enrollments\n" +
                "[3] View Students Enrolled in a Specific Course\n" +
                "[4] Back\n" +
                "Enter your choice: "
            );

            String userChoice = scanner.nextLine();

            switch (userChoice) {
                case "1" -> enrollStudent(scanner, con);
                case "2" -> viewAllEnrolledStudent(scanner, con);
                case "3" -> viewStudentsEnrolledSpecificCourse(scanner, con);
                case "4" -> { // back
                    return;
                }
                default -> System.out.println("Invalid choice");
            }

        }
        
    }

    public void viewStudentsEnrolledSpecificCourse(Scanner scanner, Connection con){

        try {
            
            PreparedStatement ps = con.prepareStatement("SELECT CourseID, CourseName FROM Course;");
            ResultSet rs = ps.executeQuery();
            System.out.println("\nCourse(s)");
            while(rs.next()){
                System.out.println(
                    "Course ID: " + rs.getInt("CourseID") + " | " + 
                    "Course Name: " + rs.getString("CourseName") + " | "
                );
            }

            System.out.print("Select a course ID to view enrolled students: ");
            String courseIDToShowStudents = scanner.nextLine();

            System.out.println();
            PreparedStatement ps1 = con.prepareStatement("SELECT CourseName FROM Course WHERE CourseID = ?;");
            ps1.setInt(1, Integer.parseInt(courseIDToShowStudents));
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                System.out.println(
                    rs1.getString("CourseName") + " Student(s)"
                );
            }

            PreparedStatement ps2 = con.prepareStatement("SELECT StudentID, FullName FROM Student WHERE CourseID = ?;");
            ps2.setInt(1, Integer.parseInt(courseIDToShowStudents));
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                System.out.println(
                    "Student ID: " + rs2.getInt("StudentID") + " | " +
                    "Full Name: " + rs2.getString("FullName") + " | "
                );
            }

        } catch (SQLException e) {
            e.getStackTrace();
        }

    }

    public void enrollStudent(Scanner scanner, Connection con){
        
        try {
            
            PreparedStatement showCourseQuery = con.prepareStatement("SELECT CourseID, CourseName FROM Course;");
            ResultSet showCourseQueryRS = showCourseQuery.executeQuery();
            System.out.println("\nCourse(s)");
            while(showCourseQueryRS.next()){
                System.out.println(
                    "Course ID: " + showCourseQueryRS.getInt("CourseID") + " | " + 
                    "Course Name: " + showCourseQueryRS.getString("CourseName") + " | "
                );
            }

            PreparedStatement showStudentQuery = con.prepareStatement("SELECT StudentID, FullName FROM Student;");
            ResultSet showStudentQueryRS = showStudentQuery.executeQuery();
            System.out.println("\nStudent(s)");
            while(showStudentQueryRS.next()){
                System.out.println(
                    "Student ID: " + showStudentQueryRS.getInt("StudentID") + " | " + 
                    "Student Name: " + showStudentQueryRS.getString("FullName") + " | "
                );
            }

            System.out.print("\nSelect Student ID to Enroll: ");
            String studentIDToEnroll = scanner.nextLine();
            
            System.out.print("Select CourseID to Enroll: ");
            String courseIDToEnroll = scanner.nextLine();
            //TODO
            PreparedStatement ps1 = con.prepareStatement(
                "SELECT s.FullName, c.CourseName " +
                "FROM Student s " +
                "JOIN Course c ON c.CourseID = ? " +
                "WHERE s.StudentID = ?;"
            );
            ps1.setInt(1, Integer.parseInt(courseIDToEnroll));
            ps1.setInt(2, Integer.parseInt(studentIDToEnroll));
            ResultSet rs1 = ps1.executeQuery();
            while(rs1.next()){
                System.out.println(
                    "\nStudent FullName: " + rs1.getString("FullName") +
                    "\nStudent Course: " + rs1.getString("CourseName")
                );
            }
            //TODO

            System.out.print("Do you confirm the enrollment of this student? (Yes/No): ");
            String yesOrNoChoice = scanner.nextLine();

            switch (yesOrNoChoice) {
                case "Yes" -> {
                    
                    PreparedStatement ps2 = con.prepareStatement("INSERT INTO Enrollment(StudentID, CourseID, EnrollDate) VALUES (?, ?, GETDATE());");
                    ps2.setInt(1, Integer.parseInt(studentIDToEnroll));
                    ps2.setInt(2, Integer.parseInt(courseIDToEnroll));
                    ps2.executeUpdate();
                    // 
                    PreparedStatement ps3 = con.prepareStatement("UPDATE Student SET CourseID = ? WHERE StudentID = ?;");
                    ps3.setInt(1, Integer.parseInt(courseIDToEnroll));
                    ps3.setInt(2, Integer.parseInt(studentIDToEnroll));
                    ps3.executeUpdate();
                    
                    System.out.println("Enrollment Successful.");
                    
                }
                case "No" -> System.out.println("Enrollment Canceled.");
                default -> System.out.println("Invalid Choice, enrollment Canceled.");
            }

        } catch (SQLException e) {
            e.getStackTrace();
        }

    }

    public void viewAllEnrolledStudent(Scanner scanner, Connection con){
            
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

        } catch (SQLException e) {
            e.getStackTrace();
        }

    }

}
