
package movieticket;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.regex.*;
public class Admin extends Sql{
    Scanner sc = new Scanner(System.in);
    
    private String dbContains(String user,String pass){
        String theatreName="";
        try{
            Connection conn = Sql.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from admin where user='"+user+"'");
            while(rs.next()){
                String pass_from_table = rs.getString("pass");
                if(pass_from_table.equals(pass)){
                    theatreName=rs.getString("theatrename");
                    return theatreName;
                }
                return theatreName;
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return theatreName;
    }
    public void login(){
        System.out.println("Enter Username: ");
        String user = sc.nextLine();
        System.out.println("Enter Password: ");
        String pass = sc.nextLine();
        if(dbContains(user,pass).equals("")){
            System.out.println("Invalid Login Credentials!");
        }
        else{
            System.out.println("Login Successful");
            Operations(dbContains(user,pass));
        }
    }
    
    private void login(String user,String pass){
        if(dbContains(user,pass).equals("")){
            System.out.println("Invalid Login Credentials!");
        }
        else{
            System.out.println("Login Successful");
            Operations(dbContains(user,pass));
        }
    }
    
    public void signup(){
        Boolean signup=true;
            while(signup){
                System.out.println("Enter Username: ");
                String user = sc.nextLine();
                System.out.println("Enter Password: ");
                String pass = sc.nextLine();
                
                if(!dbContains(user,pass).equals("")){
                    System.out.println("Email already Registered!!");
                    System.out.println("Do you want to signup again? Enter (y/n)");
                    char ch = sc.nextLine().charAt(0);
                    if(ch=='n'){
                        signup=false;
                        break;
                    }
                }
                else{
                    if(strongness(pass)){
                        System.out.println("Enter Your Theatre Name: ");
                        String thname = sc.nextLine();
                        String st = "insert into admin values('"+user+"',"+"'"+pass+"',"+"'"+thname+"')";
                        try{
                            Connection conn = Sql.getConnection();
                            Statement stmt = conn.createStatement();
                            stmt.executeUpdate(st);
                            System.out.println("Account Created Successfully!!");
                            signup=false;
                            System.out.println("Logging You in...");
                            login(user,pass);
                        }
                        catch(Exception e){
                            System.out.println(e);
                        }
                    }
                    else{
                        System.out.println("Password is Not Strong!");
                        System.out.println("Do you want to signup again? Enter (y/n)");
                        char ch = sc.nextLine().charAt(0);
                        if(ch=='n'){
                            signup=false;
                        }
                    }
                }
            }
        }
    
    private boolean strongness(String pass){
        if(pass==null)
            return false;
        String regex = "^(?=.*[0-9])"
                       + "(?=.*[a-z])(?=.*[A-Z])"
                       + "(?=.*[@#$%^&+=])"
                       + "(?=\\S+$).{8,20}$";
        return Pattern.matches(regex,pass);
    }
    MovieAdmin admin = new MovieAdmin();
    private void Operations(String thname){
        Boolean login = true;
        while(login){
            System.out.println("Enter 1 to Add Movies!");
            System.out.println("Enter 2 to Remove Movies!");
            System.out.println("Enter 3 to View Bookings for your Theatre!");
            System.out.println("Enter 4 to Logout!");
            int n=sc.nextInt();
            switch(n){
                case 1:
                    admin.addMovie(thname);
                    break;
                case 2:
                    admin .removeMovie();
                    break;
                case 3:
                    admin.myTheatreBookings(thname);
                    break;
                case 4:
                    System.out.println("Logout Successfull");
                    login=false;
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }
    }
}
