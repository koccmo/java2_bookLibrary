package java2.application_target_list.core.database.board;

import java2.application_target_list.core.domain.Record;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//@Component
public class InMemoryBoardRepositoryImpl implements BoardRepository {

    Long recordId = 0L;
    List<Record> recordsList = new ArrayList<>();


    @Override
    public void addToBoard(Record record) {
        record.setRecordId(recordId += 1);
        record.setDateAdded(getDate());
        recordsList.add(record);
//        return recordId;
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

    @Override
    public List<Record> getFullInfoAboutRecords(){
        return null;
    }

    @Override
    public List<Record> getUnfinishedRecords() {
        return null;
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
