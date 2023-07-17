

package movieticket;

import java.util.*;
public class MovieTicket {
        static Admin admin = new Admin();
        static User user = new User();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean end=true;
        while(end){
            System.out.println("Welcome To Movie Ticket Booking System!!");
            System.out.println("----------------------------------------");
            System.out.println("Enter 1 for Admin, 2 for User, 3 for exit");

            int key=sc.nextInt();
            switch(key){
                case 1:
                    System.out.println("Enter 1 for Login, 2 for Signup, 3 for exit");
                    int mode=sc.nextInt();
                    switch(mode){
                        case 1:
                            admin.login();
                            break;
                        case 2:
                            admin.signup();
                            break;
                        case 3:
                            System.out.println("Exiting from Movie Ticket Booking System!!");
                            System.out.println("Bye Bye");
                            break;
                        default:
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Enter 1 for Login, 2 for Signup, 3 for exit");
                    int mode1=sc.nextInt();
                    switch(mode1){
                        case 1:
                            user.login();
                            break;
                        case 2:
                            user.signup();
                            break;
                        default:
                            System.out.println("Exiting from Movie Ticket Booking System!!");
                            System.out.println("Bye Bye");
                            break;
                    }
                    break;
                default:
                    System.out.println("Exiting from Movie Ticket Booking System!!");
                    System.out.println("Bye Bye");
                    end=false;
                    break;
            }
        }
    }
}
