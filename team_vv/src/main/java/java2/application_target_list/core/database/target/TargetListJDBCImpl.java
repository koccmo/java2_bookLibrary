package java2.application_target_list.core.database.target;

import java2.application_target_list.core.domain.Target;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@Profile("mysql")
public class TargetListJDBCImpl implements TargetDatabase{

    @Autowired JdbcTemplate jdbcTemplate;

    @Override
    public void addTarget(Target target) {
        jdbcTemplate.update(
                "INSERT INTO targets (name, description, deadline ) " +
                        "VALUES (?, ?, ?)", target.getName(), target.getDescription(), target.getDeadline());
    }

    @Override
    public boolean deleteTarget(Long targetId) {
        jdbcTemplate.update("DELETE FROM targets WHERE id = " + targetId);
        return true;
    }

    @Override
    public boolean changeTargetName(Long targetId, String newName) {
        jdbcTemplate.update("UPDATE targets SET name = ? WHERE id = ?", newName, targetId);
        return true;
    }

    @Override
    public boolean changeTargetDescription(Long targetId, String newDescription) {
        jdbcTemplate.update("UPDATE targets SET description = ? WHERE id = ?", newDescription, targetId);
        return true;
    }

    @Override
    public boolean changeTargetDeadline(Long targetId, int newDeadline) {
        jdbcTemplate.update("UPDATE targets SET deadline = ? WHERE id = ?", newDeadline, targetId);
        return true;
    }

    @Override
    public List<Target> getTargetsList() {
        return jdbcTemplate.query("SELECT * FROM targets", new TargetsMapper());
    }

    @Override
    public boolean isIdInTargetList(Long targetId) {
        List<Target> targets = getTargetsList();

        for (Target tempTarget : targets) {
            if (tempTarget.getId().equals(targetId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Target> findByTargetName(String targetName) {
        return jdbcTemplate.query("SELECT * FROM targets WHERE name = ?", new Object[]{targetName} , new TargetsMapper());
    }

    @Override
    public List<Target> findByTargetDescription(String targetDescription) {
        return jdbcTemplate.query("SELECT * FROM targets WHERE description = ?", new Object[]{targetDescription},
                new TargetsMapper());
    }

    private class TargetsMapper implements RowMapper<Target> {
        public Target mapRow(ResultSet rs, int rowNum) throws SQLException {
            Target target = new Target(rs.getString("name"), rs.getString("description"), rs.getInt("deadline"));
            target.setId(rs.getLong("id"));
            return target;
        }
    }
}
