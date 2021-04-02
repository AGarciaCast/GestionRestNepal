package app.dao.connector;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class Connector {
    public static final String DB_URL =
      "jdbc:mysql://techsuite.live:3306/company?user=root&password=techsuite";      
     
      public Connection getConnection() {
        Connection conn = null;
         
        try {
            conn = DriverManager.getConnection(DB_URL);
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        
        return conn;
    }
      

}
