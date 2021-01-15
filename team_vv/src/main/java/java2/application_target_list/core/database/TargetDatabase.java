package java2.application_target_list.core.database;

import java2.application_target_list.core.domain.Target;

import java.util.List;

public interface TargetDatabase {

    Long addTarget(Target target);
    boolean deleteTarget(Long targetId);
    boolean changeTargetName(Long targetId, String newName);
    boolean changeTargetDescription(Long targetId, String newDescription);
    boolean changeTargetDeadline(Long targetId, int newDeadline);
    List<Target> getTargetsList();
    boolean isIdInTargetList(Long targetId);
    List<Target> findByTargetName(String targetName);
    List<Target> findByTargetDescription(String targetDescription);

}
