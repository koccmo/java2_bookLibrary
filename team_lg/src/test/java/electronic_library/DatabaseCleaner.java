package electronic_library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseCleaner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void clean() {
        for(String tableName : getTableNames()) {
            String sql0 = "DELETE FROM " + tableName;
            jdbcTemplate.execute(sql0);
            String sql1 = "ALTER TABLE " + tableName + " AUTO_INCREMENT = 1";
            jdbcTemplate.execute(sql1);
        }
    }

    private List<String> getTableNames() {
        List<String> tableNames = new ArrayList<>();
        tableNames.add("reader_books");
        tableNames.add("readers");
        tableNames.add("books");
        return tableNames;
    }

}
