package dental_clinic.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Jowl {

    private Map<Integer, ToothInfo> jowl;

    Jowl (){
        jowl = createNewJowl();
    }


    public Map<Integer, ToothInfo> getJowl() {
        return jowl;
    }

    private Map <Integer, ToothInfo> createNewJowl(){
        Map <Integer, ToothInfo> newJowl = new HashMap<>();
        for (int i = 18; i>10; i--) {
            newJowl.put(i, new ToothInfo(Optional.empty(), ToothStatus.HEALTHY));
        }
        for (int i = 21; i<29; i++) {
            newJowl.put(i, new ToothInfo(Optional.empty(), ToothStatus.HEALTHY));
        }
        for (int i = 55; i>50; i--) {
            newJowl.put(i, new ToothInfo(Optional.empty(), ToothStatus.HEALTHY));
        }
        for (int i = 61; i<66; i++) {
            newJowl.put(i, new ToothInfo(Optional.empty(), ToothStatus.HEALTHY));
        }

        for (int i = 48; i>40; i--) {
            newJowl.put(i, new ToothInfo(Optional.empty(), ToothStatus.HEALTHY));
        }
        for (int i = 31; i<39; i++) {
            newJowl.put(i, new ToothInfo(Optional.empty(), ToothStatus.HEALTHY));
        }
        for (int i = 85; i>80; i--) {
            newJowl.put(i, new ToothInfo(Optional.empty(), ToothStatus.HEALTHY));
        }
        for (int i = 71; i<76; i++) {
            newJowl.put(i, new ToothInfo(Optional.empty(), ToothStatus.HEALTHY));
        }
        return newJowl;
    }

    public boolean updateJowl(int toothNumber, Optional <String> comment, ToothStatus toothStatus) {
        if (jowl.containsKey(toothNumber)) {
            jowl.get(toothNumber).addComment(comment);
            jowl.get(toothNumber).addStatus(toothStatus);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        String result = "Jowl{\n";
        for (Integer key : jowl.keySet()){
            result += key + " " + jowl.get(key) + "\n";
        }
        return result;
    }
}
