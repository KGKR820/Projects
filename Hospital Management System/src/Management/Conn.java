package Management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {
    Connection connection;
    Statement statement;

    public Conn(){
        try{
            Class.forName("org.mariadb.jdbc.Driver");

            // Use the new application user
            connection = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/HMS",
                    "hospital_admin",  // new username
                    "hospital123"      // new password
            );
            statement = connection.createStatement();
            System.out.println("Database connected successfully with application user!");

        } catch(Exception e){
            System.err.println("Database connection failed!");
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        return connection != null && statement != null;
    }
}