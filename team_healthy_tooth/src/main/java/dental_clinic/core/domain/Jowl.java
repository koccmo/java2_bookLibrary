package dental_clinic.core.domain;

import java.util.*;

public class Jowl {

    private Map<Integer, List<ToothStatus>> jowl;

    public Jowl (){
        jowl = createNewJowl();
    }


    public Map<Integer, List<ToothStatus>> getJowl() {
        return jowl;
    }

    private Map <Integer, List<ToothStatus>> createNewJowl(){
        Map <Integer, List<ToothStatus>> newJowl = new HashMap<>();
        for (int i = 18; i>10; i--) {
            newJowl.put(i, new ArrayList<>(Arrays.asList(ToothStatus.HEALTHY)));
        }
        for (int i = 21; i<29; i++) {
            newJowl.put(i, new ArrayList<>(Arrays.asList(ToothStatus.HEALTHY)));
        }
        for (int i = 55; i>50; i--) {
            newJowl.put(i, new ArrayList<>(Arrays.asList(ToothStatus.HEALTHY)));
        }
        for (int i = 61; i<66; i++) {
            newJowl.put(i, new ArrayList<>(Arrays.asList(ToothStatus.HEALTHY)));
        }

        for (int i = 48; i>40; i--) {
            newJowl.put(i, new ArrayList<>(Arrays.asList(ToothStatus.HEALTHY)));
        }
        for (int i = 31; i<39; i++) {
            newJowl.put(i, new ArrayList<>(Arrays.asList(ToothStatus.HEALTHY)));
        }
        for (int i = 85; i>80; i--) {
            newJowl.put(i, new ArrayList<>(Arrays.asList(ToothStatus.HEALTHY)));
        }
        for (int i = 71; i<76; i++) {
            newJowl.put(i, new ArrayList<>(Arrays.asList(ToothStatus.HEALTHY)));
        }
        return newJowl;
    }



    public boolean updateJowl(int toothNumber, ToothStatus toothStatus) {
        if (jowl.containsKey(toothNumber)) {
            jowl.get(toothNumber).add(toothStatus);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jowl jowl1 = (Jowl) o;
        return Objects.equals(jowl, jowl1.jowl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jowl);
    }
}
