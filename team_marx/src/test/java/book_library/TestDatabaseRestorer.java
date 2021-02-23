package book_library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TestDatabaseRestorer {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void execute() {
        eraseTables();
        createBookTable();
        createReadersTable();
        createReadersBooksTable();
    }

    public void eraseTables() {
        for (String tableName : getTableNames()) {
            String sql = "DROP TABLE IF EXISTS " + tableName;
            jdbcTemplate.execute(sql);
        }
    }

    public void createBookTable() {
        for (String bookSql : createBookTableSql()) {
            jdbcTemplate.execute(bookSql);
        }
    }

    public void createReadersTable() {
        for (String readersSql : createReadersTableSql()) {
            jdbcTemplate.execute(readersSql);
        }
    }

    public void createReadersBooksTable() {
        for (String readersBooksSql : createReadersBooksTableSql()) {
            jdbcTemplate.execute(readersBooksSql);
        }
    }

    private List<String> getTableNames() {
        List<String> tableNames = new ArrayList<>();
        tableNames.add("reader_books");
        tableNames.add("readers");
        tableNames.add("books");
        return tableNames;
    }

    private List<String> createBookTableSql() {
        List<String> bookCreationSql = new ArrayList<>();
        bookCreationSql.add("CREATE TABLE IF NOT EXISTS books (\n" +
                "  id BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "  title VARCHAR(200) NOT NULL,\n" +
                "  author VARCHAR(100) NOT NULL,\n" +
                "  PRIMARY KEY (id)\n" +
                ")\n" +
                "ENGINE = InnoDB\n" +
                "AUTO_INCREMENT = 1;");
        bookCreationSql.add("ALTER TABLE books\n" +
                "  ADD page_count INT;");
        bookCreationSql.add("ALTER TABLE books\n" +
                "  ADD description VARCHAR(1000);");
        return bookCreationSql;
    }

    private List<String> createReadersTableSql() {
        List<String> readersCreationSql = new ArrayList<>();
        readersCreationSql.add("CREATE TABLE IF NOT EXISTS readers (\n" +
                "  id BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "  first_name VARCHAR(200) NOT NULL,\n" +
                "  last_name VARCHAR(100) NOT NULL,\n" +
                "  PRIMARY KEY (id)\n" +
                ")\n" +
                "ENGINE = InnoDB\n" +
                "AUTO_INCREMENT = 1;");
        readersCreationSql.add("ALTER TABLE readers\n" +
                "\tADD personal_code BIGINT(11) NOT NULL\n" +
                "    DEFAULT (11111111111);");
        return readersCreationSql;
    }

    private List<String> createReadersBooksTableSql() {
        List<String> readersBooksSql = new ArrayList<>();
        readersBooksSql.add("CREATE TABLE IF NOT EXISTS reader_books (\n" +
                "  id BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "  reader_id BIGINT NOT NULL,\n" +
                "  book_id BIGINT NOT NULL,\n" +
                "  book_out_date DATETIME NOT NULL,\n" +
                "  book_return_date DATETIME,\n" +
                "  PRIMARY KEY (id)\n" +
                ")\n" +
                "ENGINE = InnoDB\n" +
                "AUTO_INCREMENT = 1;");
        readersBooksSql.add("ALTER TABLE reader_books\n" +
                "ADD FOREIGN KEY (book_id) REFERENCES books(id);");
        readersBooksSql.add("ALTER TABLE reader_books\n" +
                "ADD FOREIGN KEY (reader_id) REFERENCES readers(id);");
        return readersBooksSql;
    }
}
