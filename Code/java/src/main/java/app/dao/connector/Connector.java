package app.dao.connector;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class Connector {
    public static final String DB_URL =
    		"jdbc:mysql://restnepal.mysql.database.azure.com:3306/rest?useSSL=true&requireSSL=false";       
     
      public Connection getConnection() {
        Connection conn = null;
         
        try {
            conn = DriverManager.getConnection(DB_URL, "tabernero@restnepal", "Triqui5_");
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        
        return conn;
    }
      

}
