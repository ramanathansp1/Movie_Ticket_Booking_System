
package movieticket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
public class MovieAdmin {
    Scanner sc = new Scanner(System.in);
    Connection conn = Sql.getConnection();
    
    public void addMovie(String thname){
        System.out.println("Enter the Show ID");
        int id = sc.nextInt();
        sc.nextLine();
        String y="select id from movies";
        int temp=0;
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(y);
            while(rs.next()){
                if(rs.getInt("id")==id){
                    temp=1;
                    System.out.println("This Movie ID already Exists!!!");
                    break;
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        if(temp==0){
            System.out.println("Enter the Movie Name");
            String name=sc.nextLine();
            System.out.println("Enter the Show Time");
            String time = sc.nextLine();
            System.out.println("Enter the Total Allocated Seats: ");
            String totseats = sc.nextLine();
            System.out.println("Enter the Price per Ticket");
            String price = sc.nextLine();
            String str="insert into movies values('"+id+"','"+thname+"','"+name+"','"+time+"','"+totseats+"','"+price+"')";
            try{
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(str);
                System.out.println("Movie Added Successfully!!");
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
    }
    public void removeMovie(){
        System.out.println("Enter the Show ID to delete!");
        int id = sc.nextInt();
        try{
            Statement stmt=conn.createStatement();
            stmt.executeUpdate("delete from movies where id="+id);
            System.out.println("Movie removed successfully");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public void myTheatreBookings(String thname){
        try{
            int temp=0;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select user,moviename,showtime,seats from booking where theatre='"+thname+"'");
            while(rs.next()){
                temp++;
                if(temp==1){
                    System.out.println("Username Moviename ShowTime SeatsBooked");
                }
                System.out.println(rs.getString("user")+" "+rs.getString("moviename")+" "+rs.getTime("showtime")+" "+rs.getString("seats"));
            }
            if(temp==0){
                System.out.println("No Tickets Booked Yet!");
            }                    
        }
        catch(Exception e){
            System.out.println(e);
        }
    
    }
}
