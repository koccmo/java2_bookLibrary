package java2.application_target_list.core.database.target;

import java2.application_target_list.core.domain.Target;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface TargetRepository {

    void addTarget(Target target);
    boolean deleteTarget(Long targetId);
    boolean changeTargetName(Long targetId, String newName);
    boolean changeTargetDescription(Long targetId, String newDescription);
    boolean changeTargetDeadline(Long targetId, Long newDeadline);
    List<Target> getTargetsList();
    boolean isIdInTargetList(Long targetId);
    List<Target> findByTargetName(String targetName);
    List<Target> findByTargetDescription(String targetDescription);
    Optional<Target> getById(Long id);

}
