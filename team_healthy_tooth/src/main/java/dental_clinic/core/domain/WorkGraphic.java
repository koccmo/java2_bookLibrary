package dental_clinic.core.domain;

public class WorkGraphic {


    String timesStart [] = new String [7];
    String timesEnd [] = new String [7];

    public WorkGraphic() {
        for (int i = 0; i < timesStart.length; i++) {
            timesStart[i] = "";
            timesEnd[i] = "";
        }
    }

    public String[] getTimesStart() {
        return timesStart;
    }

    public String[] getTimesEnd() {
        return timesEnd;
    }

    @Override
    public String toString() {
        return "WorkGraphic:\n" +
                "monday: " + timesStart[0] + " - " + timesEnd[0] + "\n" +
                "tuesday: " + timesStart[1] + " - " + timesEnd[1] + "\n" +
                "wednesday: " + timesStart[2] + " - " + timesEnd[2] + "\n" +
                "thursdayStart: " + timesStart[3] + " - " + timesEnd[3] + "\n" +
                "fridayStart: " + timesStart[4] + " - " +  timesEnd[4] + "\n" +
                "saturdayStart: " + timesStart[5] + " - " + timesEnd[5] + "\n" +
                "sundayStart: " + timesStart[6] + " - " + timesEnd[6] + "\n";
    }
}
