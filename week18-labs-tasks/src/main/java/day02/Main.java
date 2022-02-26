package day02;

import org.flywaydb.core.Flyway;
import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        MariaDbDataSource dataSource = new MariaDbDataSource();
        try {
            dataSource.setUrl("jdbc:mariadb://localhost:3306/bookstore?useUnicode=true");
            dataSource.setUser("root");
            dataSource.setPassword("root");
        } catch (SQLException throwables) {
            throw new IllegalStateException("Cannot reach database!");
        }

        Flyway flyway = Flyway.configure().locations("db/migration/bookstore").dataSource(dataSource).load();
        flyway.migrate();

        BooksRepository booksRepository = new BooksRepository(dataSource);
        booksRepository.updatePieces(1L,30);

//        booksRepository.insertBook("Feteke István","Téli berek", 3000,7);
//        booksRepository.insertBook("Feteke Pákó","Uurállomás", 500,7);
        System.out.println(booksRepository.findBooksByWriter("Feteke"));


    }
}
