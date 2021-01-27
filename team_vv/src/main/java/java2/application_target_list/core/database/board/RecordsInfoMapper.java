package java2.application_target_list.core.database.board;

import java2.application_target_list.core.domain.Record;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

class RecordsInfoMapper implements RowMapper<Record> {
    public Record mapRow(ResultSet rs, int rowNum) throws SQLException {
        Record record = new Record(rs.getLong("target_id"), rs.getLong("user_id"));
        record.setRecordId(rs.getLong("id"));
        record.setDateAdded(rs.getString("target_added_date"));
        record.setDateComplete(rs.getString("target_date_of_completion"));
//            record.setTargetName(rs.getString("target_name"));
//            record.setTargetDescription(rs.getString("target_description"));
//            record.setTargetDeadline(rs.getInt("target_deadline"));
//            record.setUserFirstName(rs.getString("user_first_name"));
//            record.setUserLastName(rs.getString("user_last_name"));
        return record;
    }
}
