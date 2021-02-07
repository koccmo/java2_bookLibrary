package dental_clinic.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseCleanerClinic {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void clean() {
        for(String tableName : getTableNames()) {
            String sql = "delete from " + tableName;
            jdbcTemplate.execute(sql);
        }
    }

    private List<String> getTableNames() {
        List<String> tableNames = new ArrayList<>();
        tableNames.add("doctorsWorkGraphic");
        tableNames.add("jowl");
        tableNames.add("plannedVisit");
        tableNames.add("visit");
        tableNames.add("doctor");
        tableNames.add("personalData");
        tableNames.add("manipulation");
        return tableNames;
    }

}
