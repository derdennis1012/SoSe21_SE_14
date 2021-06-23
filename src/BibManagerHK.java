import java.util.Scanner;

public class BibManagerHK {

    protected boolean isLoggedIn = false;
    Scanner sc = new Scanner(System.in);
    LoginHK login = new LoginHK();
    public DatabaseBib db = new DatabaseBib();

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
        while(true){
            mainMenu();
        }
    }

    public void mainMenu(){
            System.out.println("Please select what you want to do and press ENTER");
            System.out.println("[1] Login as Employee");
            System.out.println("[2] Login with your Member-Card");
            System.out.println("[3] Reboot");
            String in = sc.nextLine();

            switch (in){
                case "1":
                   isLoggedIn = loginEmployee();
                    if(isLoggedIn == false) mainMenu();
                    else showEmployeeMenu();
                    break;
                case "2":
                    isLoggedIn = loginMemeber();
                    if(!isLoggedIn) mainMenu();
                    else isLoggedIn = true;
                    break;
                case "3":
                    start();
                    break;
            }


    }
    public boolean loginEmployee(){
        System.out.println("Username: ");
        String username = sc.nextLine();
        System.out.println("Password: ");
        String password = sc.nextLine();
        if(db.login(username,password)){
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
    public boolean addUser(){
        System.out.println("Please enter the username");
        String username = sc.nextLine();
        System.out.println("Please enter the password");
        String password = sc.nextLine();
        db.add_user(username,password);
        return true;
    }

    public void addBook(){
        System.out.println("Please enter the Title");
        String title = sc.nextLine();
        System.out.println("Please enter the Field");
        String field= sc.nextLine();
        System.out.println("Please enter the LocationID");
        String storage_location = sc.nextLine();
        System.out.println("Please enter the ISBN");
        String isbn = sc.nextLine();
        System.out.println("Please enter the Edition");
        int edition = sc.nextInt();
         try{
             db.add_book(title,field,Integer.parseInt(storage_location),isbn,edition);
         }catch(Exception e){
             System.out.println(e.getMessage());
         }
    }

    public void showEmployeeMenu(){
        System.out.println("Please select what you want to do and press ENTER");
        System.out.println("[1] Add Book");
        System.out.println("[2] Add user");
        System.out.println("[3] Lend Book");
        System.out.println("[4] Lend Book");
        System.out.println("[5] Reboot");
        String in = sc.nextLine();

        switch (in){
            case "1":
               addBook();
               showEmployeeMenu();
                break;
            case "2":
                addUser();
                showEmployeeMenu();
                break;
            case "3":
                start();
                break;
            case "4":
                searchBook();
                showEmployeeMenu();
                break;
            case "5":
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

    public void searchBook(){
        System.out.println("Please enter the book name");
        String q = sc.nextLine();
        String bName = db.search(q);
        System.out.println("The Book is: " + bName);
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
