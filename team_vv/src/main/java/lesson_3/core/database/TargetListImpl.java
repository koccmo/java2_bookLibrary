package lesson_3.core.database;


import lesson_3.Target;

import java.util.ArrayList;
import java.util.List;

public class TargetListImpl implements Database {

    List<Target> targetsList = new ArrayList<>();
    Long targetId = 0L;

    @Override
    public Long addTarget(Target target) {
        target.setId(targetId += 1);
        targetsList.add(target);
        return target.getId();
    }

    @Override
    public boolean deleteTarget(Long targetId) {
        if (isTargetInList(targetId)) {
            targetsList.remove(getTargetIndexFromListById(targetId));
            return true;
        }
        return false;
    }

    @Override
    public boolean changeTargetName(Long targetId, String newName) {
        if (isTargetInList(targetId)){
            targetsList.get(getTargetIndexFromListById(targetId)).setName(newName);
            return true;
        }
        return false;
    }

    @Override
    public boolean changeTargetDescription(Long targetId, String newDescription) {
        if (isTargetInList(targetId)){
            targetsList.get(getTargetIndexFromListById(targetId)).setDescription(newDescription);
            return true;
        }
        return false;
    }

    @Override
    public boolean changeTargetDeadline(Long targetId, int newDeadline) {
        if (isTargetInList(targetId)){
            targetsList.get(getTargetIndexFromListById(targetId)).setDeadline(newDeadline);
            return true;
        }
        return false;
    }

    private boolean isTargetInList(Long targetId) {
        for (Target tempTarget : targetsList) {
            if (tempTarget.getId().equals(targetId)) {
                return true;
            }
        }
        return false;
    }

    private int getTargetIndexFromListById(Long targetId) {
        for (int i = 0; i < targetsList.size(); i++) {
            if (targetsList.get(i).getId().equals(targetId)) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public List<Target> getTargetsList() {
        return targetsList;
    }
}
