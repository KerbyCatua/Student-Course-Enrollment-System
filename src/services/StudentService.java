package services;

import java.sql.*;
import java.util.*;

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
                case "1" -> { // add new student

                    System.out.print("Enter Student Full Name: ");
                    String fullName = scanner.nextLine();

                    System.out.print("Enter Student Email Name: ");
                    String email = scanner.nextLine();

                    System.out.print("Enter Student Age Name: ");
                    String age = scanner.nextLine();

                    try {
                        PreparedStatement ps = con.prepareStatement("");
                    } catch (SQLException e) {
                        e.getStackTrace();
                    }

                }
                case "2" -> System.out.println();
                case "3" -> System.out.println();
                case "4" -> System.out.println();
                case "5" -> { // back
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }

    }

}
