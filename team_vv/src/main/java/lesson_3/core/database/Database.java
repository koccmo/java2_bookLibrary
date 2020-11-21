package lesson_3.core.database;

import lesson_3.Target;

import java.util.List;

public interface Database {

    Long addTarget(Target target);
    boolean deleteTarget(Long targetId);
    boolean changeTargetName(Long targetId, String newName);
    boolean changeTargetDescription(Long targetId, String newDescription);
    boolean changeTargetDeadline(Long targetId, int newDeadline);
    List<Target> getTargetsList();


}
