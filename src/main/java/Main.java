import com.codecool.fileshare.repository.ImageJdbcRepository;

public class Main {
    public static void main(String[] args) {
        ImageJdbcRepository jd = new ImageJdbcRepository();
        jd.storeImage("cat", "catcat");
    }
}
