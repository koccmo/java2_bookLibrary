package java2.application_target_list.core.database;

import java2.application_target_list.core.domain.Record;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.domain.User;

import java.util.List;

public interface BoardDatabase {

    Long addToBoard(Record record);
    boolean deleteFromBoard(Long id);
    List<Record> getAllRecordsList();
    boolean setRecordCompleteDate(Long id);
    public boolean isIdInBoardList(Long boardId);
    List<Target> findTargetsByUserId(Long userId);
    List<Target> findTargetByTargetId(Long targetId);
    List<User> findUserByTargetId(Long targetId);
    List<User> findUserByUserId(Long userId);
}
