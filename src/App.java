import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {
        
        try {

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
        }

    }
}
