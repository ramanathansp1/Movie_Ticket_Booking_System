
package movieticket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.regex.Pattern;
public class User extends Sql{
    Scanner sc = new Scanner(System.in);
    
    private boolean dbContains(String user,String pass){
        try{
            Connection conn = Sql.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from user where user='"+user+"'");
            while(rs.next()){
                String pass_from_table = rs.getString("pass");
                if(pass_from_table.equals(pass)){
                    return true;
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    
    public void login(){
        System.out.println("Enter Username: ");
        String user = sc.nextLine();
        System.out.println("Enter Password: ");
        String pass = sc.nextLine();
        if(!dbContains(user,pass)){
            System.out.println("Invalid Login Credentials!");
        }
        else{
            System.out.println("Login Successful");
            Operations(user);
        }
    }
    
    private void login(String user,String pass){
        if(!dbContains(user,pass)){
            System.out.println("Invalid Login Credentials!");
        }
        else{
            System.out.println("Login Successful");
            Operations(user);
        }
    }
    
    public void signup(){
        Boolean signup=true;
            while(signup){
                System.out.println("Enter Username: ");
                String user = sc.nextLine();
                System.out.println("Enter Password: ");
                String pass = sc.nextLine();
                
                if(dbContains(user,pass)){
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
                        String st = "insert into user values('"+user+"',"+"'"+pass+"')";
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
    MovieUser user1 = new MovieUser();
    private void Operations(String user){
        Boolean login = true;
        while(login){
            System.out.println("Enter 1 to Search Available Movies!");
            System.out.println("Enter 2 to Book Tickets!");
            System.out.println("Enter 3 to View Tickets booked by you!");
            System.out.println("Enter 4 to Logout!");
            int n=sc.nextInt();
            switch(n){
                case 1:
                    user1.search();
                    break;  
                case 2:
                    user1.book(user);
                    break;
                case 3:
                    user1.view(user);
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
