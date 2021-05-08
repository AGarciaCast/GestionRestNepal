package app.dao.connector;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class Connector {
    public static final String DB_URL =
    //		"jdbc:mysql://restnepal.mysql.database.azure.com:3306/rest?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC";
            "jdbc:mysql://34.76.59.62:3306/rest?useSSL=true&requireSSL=false&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC";

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
      
      public static void main(String[]args){
    	  Connector c = new Connector();
    	  Connection conn = c.getConnection();
    	  System.out.println(conn);
    	  
      }
}
