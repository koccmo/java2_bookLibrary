package java2.application_target_list.core.database;

import java2.application_target_list.core.domain.Record;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.domain.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class BoardListImpl implements BoardDatabase{

    Long recordId = 0L;
    List<Record> recordsList = new ArrayList<>();


    @Override
    public Long addToBoard(Record record) {
        record.setRecordId(recordId += 1);
        record.setDateAdded(getDate());
        recordsList.add(record);
        return recordId;
    }

    @Override
    public boolean deleteFromBoard(Long id) {
        if (isIdInBoardList(id)){
            recordsList.remove(getBoardIndexFromListById(id));
            return true;
        }
        return false;
    }

    @Override
    public List<Record> getAllRecordsList() {
        return recordsList;
    }

    @Override
    public boolean setRecordCompleteDate(Long id) {
        if (isIdInBoardList(id)){
            recordsList.get(getBoardIndexFromListById(id)).setDateComplete(getDate());
            return true;
        }
        return false;
    }

    @Override
    public List<Target> findTargetsByUserId(Long userId) {
        return null;
    }

    @Override
    public List<Target> findTargetByTargetId(Long targetId) {
        return null;
    }

    @Override
    public List<User> findUserByTargetId(Long targetId) {
        return null;
    }

    @Override
    public List<User> findUserByUserId(Long userId) {
        return null;
    }

    private String getDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(myFormatDate);
    }

    @Override
    public boolean isIdInBoardList(Long boardId) {
        for (Record tempRecord : recordsList) {
            if (tempRecord.getRecordId().equals(boardId)) {
                return true;
            }
        }
        return false;
    }

    private int getBoardIndexFromListById(Long boardId) {
        for (int i = 0; i < recordsList.size(); i++) {
            if (recordsList.get(i).getRecordId().equals(boardId)) {
                return i;
            }
        }
        return 0;
    }
}
