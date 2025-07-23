import java.sql.Connection;
import java.util.*;

import services.CourseService;
import services.StudentService;

public class MainServices {

    static{
        System.out.println("Developed by: Jherald Kerby Catua | 1ST YEAR BSIT STUDENT");
    }

    public void landingPage(Scanner scanner, StudentService studentService, CourseService courseService, Connection con){

        while(true){

            System.out.print(
                "\nWelcome to Student Course Enrollment System\n" +
                "[1] Student Module\n" +
                "[2] Course Module\n" +
                "[3] Enrollment Module\n" +
                "[4] Exit\n" +
                "Enter your choice: "
            );
        
            String userChoice = scanner.nextLine();
            switch (userChoice) {
                case "1" -> { // student module
                    studentService.studentModule(scanner, con);
                }
                case "2" -> { // course module
                    courseService.courseModule(scanner, con);
                } 
                case "3" -> { // enrollment module

                } 
                case "4" -> {
                    System.out.println("The program is exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice");
            }

        }

    }

}
