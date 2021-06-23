import java.util.Scanner;

public class LoginHK {

    Scanner sc = new Scanner(System.in);
    public boolean loginEmployee(){
        System.out.println("Username: ");
        String username = sc.nextLine();
        System.out.println("Password: ");
        String password = sc.nextLine();
       if(username.equals("db") && password.equals("abc")){
          System.out.println("Login done!");
           return true;
        }else{
            System.out.println("Oops did you forgot you details? Now you need the guys in the IT!");
           return false;
        }

    };
    public boolean loginMemeber(){
        System.out.println("Please scann your card: ");
        String number = sc.nextLine();

        if(number.equals("1234")){
            System.out.println("Login done!");
            return true;
        }else{
            System.out.println("Your card is not valid!");
            return false;
        }
    };

}
