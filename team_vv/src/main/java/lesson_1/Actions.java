package lesson_1;

interface Actions {

    Long addTarget(Target target);
    boolean deleteTarget(Long targetId);
    boolean changeTargetName(Long targetId, String newName);
    boolean changeTargetDescription(Long targetId, String newDescription);
    boolean changeTargetDeadline(Long targetId, int newDeadline);



}