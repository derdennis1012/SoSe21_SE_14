import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseBib implements Persistence {

    protected Statement books=null;
    protected Statement users=null;

    public DatabaseBib(){
        generate_bookdb();
        generate_usersdb();
    }


    public boolean add_book(String title, String field, int storage_location, String isbn, int edition){
        try {
            String query = "INSERT INTO BOOKS (title, field, storage_location, isbn, edition) VALUES ('" + title + "', '" + field + "', '" + storage_location + "', '" + isbn + "', '" + edition + "')";
            ResultSet rs = books.executeQuery(query);
            rs.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean login(String name, String password){
        String query;
        ResultSet rs;
        try {
            query = "SELECT username AS count FROM USERS WHERE username = '" + name + "' AND password = '"+password+"' ;";
            rs = users.executeQuery(query);
            int rsCount = 0;
            while(rs.next())
            {
                rsCount = rsCount + 1;
            }
            if(rsCount == 1){
                return true;
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean add_user(String name, String password){
        try {
            String query = "INSERT INTO USERS (username, password) VALUES ('" + name + "', '" + password + "')";
            ResultSet rs = users.executeQuery(query);
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public String search(String title){
        String query, result=null;
        ResultSet rs;
        try {
            query = "SELECT * FROM BOOKS WHERE title LIKE '" + title +"%';";
            rs = books.executeQuery(query);
            while (rs.next()) {
                result = rs.getString("title") + rs.getString("field") + rs.getString("storage_location") + rs.getString("isbn") + rs.getString("edition");
            }
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String lend_book(String isbn){
        String query, result=null;
        ResultSet rs;
        try {
            query = "SELECT * FROM BOOKS WHERE isbn = '" + isbn +"';";
            rs = books.executeQuery(query);
            while (rs.next()) {
                result = rs.getString("title") + rs.getString("field") + rs.getString("storage_location") + rs.getString("isbn") + rs.getString("edition");
            }
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private void generate_bookdb() {
        Statement stmt = null;
        Connection c=null;
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            c = DriverManager.getConnection("jdbc:hsqldb:mem:bookdatabase;shutdown=true", "root", "root");
            String query = "CREATE TABLE IF NOT EXISTS BOOKS (title CHAR(200), field CHAR(24), storage_location char(5), isbn char(15), edition char(5));";
            stmt = c.createStatement();
            stmt.executeQuery(query);
            books = stmt;
            add_book("Methodische objektorientierte Softwareentwicklung: Eine Integration klassischer und moderner Entwicklungskonzepte", "Informatik", 2, "9783898642736", 1);
            add_book("Sollte man mit Java eine Website programmieren - kurz: NEIN!", "Informatik", 1, "9783898642737", 5);
            add_book("JavaScriptIsTheBestLanguageOfTheWorld!", "Informatik", 1, "9783898642738", 1);
            add_book("Die Kaenguru Chroniken: Ansichten eines vorlauten Beuteltiers", "Fiction", 3, "9783548372570", 7);
            add_book("Der erste letzte Tag: Kein Thriller", "Bestseller", 3, "9783426283868", 7);
            add_book("Energy!: Der gesunde Weg aus dem Müdigkeitslabyrinth", "Bestseller", 3, "9783423282772", 1);
            add_book("Deutsches Schulsystem RELOADED - Wie es besser gehen würde...", "Bestseller", 3, "9783423282774", 300);
            add_book("Hensslers schnelle Nummer: 100 neue Rezepte zum Erfolgsformat", "Bestseller", 3, "9783833877773", 3);
            add_book("Der liebste Papa der Welt!", "Bestseller", 3, "9783789173509", 1);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

        }
    }

    private void generate_usersdb() {
        ResultSet rs;
        Statement stmt = null;
        Connection c=null;
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            c = DriverManager.getConnection("jdbc:hsqldb:mem:usersdatabase;shutdown=true", "root", "root");
            String query = "CREATE TABLE IF NOT EXISTS USERS (username CHAR(200), password CHAR(24));";
            stmt = c.createStatement();
            stmt.executeQuery(query);
            users=stmt;
            add_user("admin", "admin");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

        }
    }


}


