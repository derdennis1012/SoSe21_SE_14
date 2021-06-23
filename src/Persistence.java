import java.sql.Statement;

public interface Persistence {

    public boolean add_book(String title, String field, int storage_location, String isbn, int edition);

    public boolean login(String name, String password);

    public boolean add_user(String name, String password);

    public String search(String title);

    public String lend_book(String isbn);

}
