package dental_clinic.core.database.manipulation;

import dental_clinic.core.database.doctor.DoctorRowMapper;
import dental_clinic.core.domain.Manipulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManipulationJdbcDatabaseImpl implements ManipulationDatabase {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void addManipulation(Manipulation manipulation) {
        jdbcTemplate.update(
                "INSERT INTO service (service_type, price, isActive) "
                + "VALUES (?, ?, ?)",
                manipulation.getManipulation_type(), manipulation.getPrice(), manipulation.getIsActive()
        );
    }

    @Override
    public List<Manipulation> getManipulationsList() {
        String sql = "SELECT * FROM service";
        return jdbcTemplate.query(sql, new ManipulationRowMapper());
    }

    @Override
    public void deactivateManipulation(Long id) {
        jdbcTemplate.update(
                "UPDATE service " +
                    "SET isActive = " + false +
                    " WHERE id = " + id + ";");
    }

    @Override
    public boolean containsTheSameManipulation(Manipulation manipulation) {
        String sql = "SELECT * FROM service WHERE service_type = \""
                + manipulation.getManipulation_type() +"\";";
        return jdbcTemplate.query(sql, new ManipulationRowMapper()).size() == 1;
    }

    @Override
    public boolean manipulationIsActive(Long id) {
        String sql = "SELECT * FROM service WHERE id = " + id;
        return jdbcTemplate.query(sql, new ManipulationRowMapper()).get(0).getIsActive();
    }

    @Override
    public boolean containsId(Long id) {
        String sql = "SELECT * FROM service WHERE id = " + id + ";";
        return jdbcTemplate.query(sql, new ManipulationRowMapper()).size() == 1;
    }
}
