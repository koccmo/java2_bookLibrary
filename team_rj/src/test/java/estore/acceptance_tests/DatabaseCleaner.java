package estore.acceptance_tests;

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
            String sql = "delete from " + tableName;
            jdbcTemplate.execute(sql);
        }
        jdbcTemplate.execute("ALTER TABLE eStore_test.productCategory AUTO_INCREMENT=1");
        jdbcTemplate.execute("ALTER TABLE eStore_test.products AUTO_INCREMENT=1");
    }

    private List<String> getTableNames() {
        List<String> tableNames = new ArrayList<>();
        tableNames.add("eStore_test.order_details");
        tableNames.add("eStore_test.orders");
        tableNames.add("eStore_test.clients");
        tableNames.add("eStore_test.products");
        tableNames.add("eStore_test.productCategory");
        return tableNames;
    }
}
