package services;

import java.util.Scanner;

import java.sql.*;

import models.*;

public class CourseService {

    public void courseModule(Scanner scanner, Connection con){

        while(true){
            
            System.out.print(
                "\n-- Course Module --\n" +
                "[1] Add New Course\n" +
                "[2] View All Courses\n" +
                "[3] Edit Course Info\n" +
                "[4] Delete Course\n" +
                "[5] Back\n" +
                "Enter your choice: "
            );

            String userChoice = scanner.nextLine();

            switch (userChoice) {
                case "1" -> addCourse(scanner, con);
                case "2" -> viewAllCourses(con);
                case "3" -> editCourseInfo(scanner, con);
                case "4" -> deleteCourse(scanner, con);
                case "5" -> { // back
                    return;
                }
                default -> System.out.println("Invalid choice");
            }

        }

    }

    public void addCourse(Scanner scanner, Connection con){

        try {

            System.out.print("Enter the name of new course to add: ");
            String newCourse = scanner.nextLine();

            Course course = new Course(newCourse);
            PreparedStatement ps = con.prepareStatement("INSERT INTO Course(CourseName) VALUES (?);");
            ps.setString(1, course.getNewCourse());
            ps.executeUpdate();
            System.out.println("New course has been added.");

        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    public void viewAllCourses(Connection con){

        try {
            
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Course;");
            ResultSet rs = ps.executeQuery();

            System.out.println();
            while(rs.next()){
                System.out.println(
                    "Course ID: [" + rs.getInt("CourseID") + "] | " + 
                    "Course Name: " + rs.getString("CourseName") + " | "
                );
            }

        } catch (SQLException e) {
            e.getStackTrace();
        }

    }

    public void editCourseInfo(Scanner scanner, Connection con){

        try {
            
            System.out.println();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Course;");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                System.out.println(
                    "Course ID [" + rs.getInt(1) + "] | " + 
                    rs.getString(2) + " | "
                );
            }

            System.out.print("\nEnter CourseID to Modify: ");
            String courseIDToModify = scanner.nextLine();

            PreparedStatement ps1 = con.prepareStatement("SELECT * FROM Course WHERE CourseID = ?;");
            ps1.setInt(1, Integer.parseInt(courseIDToModify));
            ResultSet rs1 = ps1.executeQuery();

            while(rs1.next()){
                System.out.println(
                    rs1.getInt("CourseID") + " | " + rs1.getString("CourseName")
                );
            }

            System.out.print("New Course Name: ");
            String newCourseName = scanner.nextLine();
            Course course = new Course(newCourseName);

            PreparedStatement ps2 = con.prepareStatement("UPDATE Course SET CourseName = ? WHERE CourseID = ?;");
            ps2.setString(1, course.getNewCourse());
            ps2.setInt(2, Integer.parseInt(courseIDToModify));
            ps2.executeUpdate();

            System.out.println("Updated new Course Name.");
            

        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    public void deleteCourse(Scanner scanner, Connection con){

        try {

            System.out.println();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Course;");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                System.out.println(
                    "Course ID [" + rs.getInt(1) + "] | " + 
                    rs.getString(2) + " | "
                );
            }
            
            System.out.print("\nEnter CourseID to Delete: ");
            String courseIDToDelete = scanner.nextLine();
            Course course = new Course(courseIDToDelete);

            PreparedStatement ps1 = con.prepareStatement("DELETE FROM Course WHERE CourseID = ?;");
            ps1.setInt(1, Integer.parseInt(course.getNewCourse()));
            ps1.executeUpdate();
            System.out.println("Course Deleted");

        } catch (SQLException e) {
            e.getStackTrace();
        }

    }

}
