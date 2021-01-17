package java2.application_target_list.core.database.board;

import java2.application_target_list.core.domain.Record;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.domain.User;

import java.util.List;

public interface BoardDatabase {

    void addToBoard(Record record);
    boolean deleteFromBoard(Long id);
    List<Record> getAllRecordsList();
    boolean setRecordCompleteDate(Long id);
    public boolean isIdInBoardList(Long boardId);
}
