package movieticket;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
public class Sql {
    static Connection conn=null;
    public static Connection getConnection(){
        if(conn!=null)
            return conn;
        String database = "Movie_Ticket";
        String username = "root";
        String password = "ramanathansp1@";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+database,username,password);
            return conn;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return conn;        
    }
    
}
