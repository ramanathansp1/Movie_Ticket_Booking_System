
package movieticket;
import java.util.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
public class MovieUser {
    Scanner sc = new Scanner(System.in);
    Connection conn = Sql.getConnection();
   public void search(){
       boolean find=true;
       while(find){
            System.out.println("Enter 1 - To searh by Movie Name");
            System.out.println("Enter 2 - To search by Theatre Name");
            System.out.println("Enter 3 - To view All available movies");
            System.out.println("ENter 4 - To Exit");
            int n=sc.nextInt();
            sc.nextLine();
            switch(n){
                case 1:
                    System.out.println("Enter Movie Name: ");
                    String moviename = sc.nextLine();
                    System.out.println();
                    System.out.println("Showing available Theatres for "+moviename+" movie:");
                    String st = "select id,theatrename,showtime,price from movies where moviename='"+moviename+"'";
                    try{
                        int temp=0;
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(st);
                        while(rs.next()){
                            temp++;
                            if(temp==1){
                                System.out.println("ID Theatre ShowTime Price");
                            }
                             System.out.println(rs.getInt("id")+" "+rs.getString("theatrename")+" "+rs.getTime("showtime")+" "+rs.getInt("price"));
                        }
                        if(temp==0){
                            System.out.println("No Theatres Available for this Movie");
                        }
                        System.out.println();
                        System.out.println();
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                    break;
                case 2:
                    System.out.println("Enter Theatre Name :");
                    String theatrename = sc.nextLine();
                    System.out.println();
                    System.out.println("Displaying All Movies in "+theatrename+" theatre");
                    String st1 = "select id,moviename,showtime,price from movies where theatrename='"+theatrename+"'";
                    try{
                        int temp=0;
                        Statement stmt =conn.createStatement();
                        ResultSet rs = stmt.executeQuery(st1);
                        while(rs.next()){
                            temp++;
                            if(temp==1){
                                System.out.println("ID Movie ShowTime Price");
                            }
                            System.out.println(rs.getInt("id")+" "+rs.getString("moviename")+" "+rs.getTime("showtime")+" "+rs.getInt("price"));
                        }
                        if(temp==0){
                            System.out.println("No Movies Available in this Theatre");
                        }
                        System.out.println();
                        System.out.println();
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                    break;
                case 3:
                     System.out.println("Displaying all Shows:");
                     String st2 = "select * from movies";
                     System.out.println();
                     try{
                         int temp=0;
                         Statement stmt =conn.createStatement();
                         ResultSet rs =stmt.executeQuery(st2);
                         while(rs.next()){
                             temp++;
                            if(temp==1){
                                System.out.println("ID Theatre MovieName ShowTime RemSeats Price");
                            }
                             System.out.println(rs.getInt("id")+" "+rs.getString("theatrename")+" "+rs.getString("moviename")+" "+rs.getTime("showtime")+" "+rs.getInt("totalseats")+" "+rs.getInt("price"));
                         }
                         if(temp==0){
                             System.out.println("No movies are displayed around you!!!");
                         }
                         System.out.println();
                         System.out.println();
                     }
                     catch(Exception e){
                         System.out.println(e);
                     }
                     break;
                case 4:
                    System.out.println("Exiting Search....");
                    find=false;
                    break;
                default:
                    System.out.println("Invalid Input !!");
                    break;
            }
       }
       
   }
   public void book(String user){
       System.out.println("Enter Movie ID to Book Ticket!");
       int id = sc.nextInt();
       sc.nextLine();
       String st3="select * from movies where id="+id;
       
       String mn="";
       String tn="";
       String time="";
       int totalseats=0;
       int price=0;
       try{
           Statement stmt = conn.createStatement();
           ResultSet rs = stmt.executeQuery(st3);
           rs.next();
           if(rs.getString("theatrename").equals("")){
               System.out.println("No Movie is available with id "+id);
           }
           else{
                mn=rs.getString("moviename");
                tn=rs.getString("theatrename");
                time=rs.getString("showtime");
                totalseats=rs.getInt("totalseats");
                price=rs.getInt("price");
            }
       }
       catch(Exception e){
           System.out.println(e);
       }
       
       System.out.println("Enter No. of Tickets You Need?");
       int need=sc.nextInt();
       sc.nextLine();
       if(need<=totalseats){
            System.out.println("Enter Seat Numbers as A1,A2...");
            System.out.println("Seats available are: A 1-20, B 1-20 and C 1-20");
            String seats=sc.nextLine();



            String st4="insert into booking values('"+user+"','"+mn+"','"+tn+"','"+time+"','"+seats+"')";
             try{
                 Statement stmt1 = conn.createStatement();
                 stmt1.executeUpdate(st4);
                 System.out.println("Tickets Booked Successfully!!");
                 System.out.println("-----------------------------");
                 System.out.println("Your Total Cost is : "+(price*need));
                 System.out.println("-----------------------------");
                 int ans=totalseats-need;
                 stmt1.executeUpdate("update movies set totalseats="+ans+" where id="+id);
             }
             catch(Exception e){
                 System.out.println(e);
             }
       }
       else{
           System.out.println("Sorry! Only "+ totalseats+" tickets are Available!!");
       }
   }
   public void view(String user){
       System.out.println();
       String st5 = "select * from booking where user='"+user+"'";
       try{
           int temp=0;
           Statement stmt = conn.createStatement();
           ResultSet rs = stmt.executeQuery(st5);
           while(rs.next()){
               temp++;
               if(temp==1){
                   System.out.println("MovieName Theatre ShowTime Seats");
               }
               System.out.println(rs.getString("moviename")+" "+rs.getString("theatre")+" "+rs.getTime("showtime")+" "+rs.getString("seats"));
           }
           if(temp==0){
               System.out.println("No Booking History!!!");
           }
           System.out.println();
           System.out.println();
       }
       catch(Exception e){
           System.out.println(e);
       }
   }
}
