import java.sql.*;
import java.util.*;

import services.StudentService;

public class Main {
    public static void main(String[] args) {
        
        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=StudentDB;integratedSecurity=true;encrypt=true;trustServerCertificate=true";
            Connection con = DriverManager.getConnection(url);

            Scanner scanner = new Scanner(System.in);

            StudentService studentService = new StudentService();
            MainServices mainServices = new MainServices();
            mainServices.landingPage(scanner, studentService, con);

            scanner.close();
        } catch (SQLException e) {
            e.getStackTrace();
        }

    }
}

/*try {

            String url = "jdbc:sqlserver://localhost:1433;databaseName=StudentDB;integratedSecurity=true;encrypt=true;trustServerCertificate=true";
            Connection con = DriverManager.getConnection(url);

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Enrollment;");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                System.out.println(
                    rs.getInt("EnrollmentID") + " | " +
                    rs.getInt("StudentID") + " | " +
                    rs.getInt("CourseID") + " | " +
                    rs.getDate("EnrollDate")    
                    );
            }

        } catch (SQLException e) {
            e.getStackTrace();
        } */