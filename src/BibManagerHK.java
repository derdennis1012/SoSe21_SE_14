import java.util.Scanner;

public class BibManagerHK {

    protected boolean isLoggedIn = false;
    Scanner sc = new Scanner(System.in);
    LoginHK login = new LoginHK();

    public void start(){
        System.out.println("Welcome to the ultimative BibManager \n");
        wait(100);
        System.out.println("Version 4.0.3 © L&D Dev Studio - 1998");
        wait(500);
        System.out.println("\n");

        System.out.print("Booting ");
        for (int i = 0; i<15; i++){
            wait(350);
            System.out.print(".");
        }
        System.out.println("\n");
        wait(500);
        System.out.print("System check running ");
        for (int i = 0; i<10; i++){
            wait(200);
            System.out.print(".");
        }
        System.out.println("\n");
        System.out.println("All Systems are working fine!");
        wait(500);
        System.out.println("Launching BibManager ...");
        System.out.println("\n");
        wait(500);
        System.out.println("BibManager 4.0.3 © L&D Dev Studio - 1998 \n");
        mainMenu();
    }

    public void mainMenu(){
            System.out.println("Please select what you want to do and press ENTER");
            System.out.println("[1] Login as Employee");
            System.out.println("[2] Login with your Member-Card");
            System.out.println("[3] Reboot");
            String in = sc.nextLine();

            switch (in){
                case "1":
                   isLoggedIn = login.loginEmployee();
                    if(isLoggedIn == false) mainMenu();
                    else showEmployeeMenu();
                    break;
                case "2":
                    isLoggedIn = login.loginMemeber();
                    if(!isLoggedIn) mainMenu();
                    else isLoggedIn = true;
                    break;
                case "3":
                    start();
                    break;
            }


    }

    public void showEmployeeMenu(){
        System.out.println("Please select what you want to do and press ENTER");
        System.out.println("[1] Add Book");
        System.out.println("[2] Add member");
        System.out.println("[3] List Books");
        System.out.println("[4] Reboot");
        String in = sc.nextLine();

        switch (in){
            case "1":
                isLoggedIn = login.loginEmployee();
                if(isLoggedIn == false) mainMenu();
                else showEmployeeMenu();
                break;
            case "2":
                isLoggedIn = login.loginMemeber();
                if(!isLoggedIn) mainMenu();
                else {
                    showMemberMenu();
                }
                break;
            case "3":
                start();
                break;
            case "4":
                start();
                break;
        }
    }
    public void showMemberMenu(){
        System.out.println("Please select what you want to do and press ENTER");
        System.out.println("[1] List Books");
        System.out.println("[3] Reboot");
        String in = sc.nextLine();

        switch (in){
            case "1":
                isLoggedIn = login.loginEmployee();
                if(isLoggedIn == false) mainMenu();
                else showEmployeeMenu();
                break;
            case "2":
                isLoggedIn = login.loginMemeber();
                if(!isLoggedIn) mainMenu();
                else isLoggedIn = true;
                break;
            case "3":
                start();
                break;
        }
    }
    public static void wait(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }


    public static void main(String[] args) {
        BibManagerHK b = new BibManagerHK();
        b.start();
    }
}
