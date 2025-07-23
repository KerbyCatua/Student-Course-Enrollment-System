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
                case "3" -> System.out.println(); // TODO edit course info
                case "4" -> System.out.println(); // TODO delete course
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

}
