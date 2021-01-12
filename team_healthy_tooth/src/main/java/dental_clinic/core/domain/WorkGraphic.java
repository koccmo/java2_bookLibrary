package dental_clinic.core.domain;

public class WorkGraphic {

    private String mondayStart = "";
    private String mondayEnd = "";
    private String tuesdayStart = "";
    private String tuesdayEnd;
    private String wednesdayStart;
    private String wednesdayEnd;
    private String thursdayStart;
    private String thursdayEnd;
    private String fridayStart;
    private String fridayEnd;
    private String saturdayStart;
    private String saturdayEnd;
    private String sundayStart;
    private String sundayEnd;

    public WorkGraphic(String mondayStart, String mondayEnd, String tuesdayStart, String tuesdayEnd,
                       String wednesdayStart, String wednesdayEnd, String thursdayStart, String thursdayEnd,
                       String fridayStart, String fridayEnd, String saturdayStart, String saturdayEnd,
                       String sundayStart, String sundayEnd) {
        this.mondayStart = mondayStart;
        this.mondayEnd = mondayEnd;
        this.tuesdayStart = tuesdayStart;
        this.tuesdayEnd = tuesdayEnd;
        this.wednesdayStart = wednesdayStart;
        this.wednesdayEnd = wednesdayEnd;
        this.thursdayStart = thursdayStart;
        this.thursdayEnd = thursdayEnd;
        this.fridayStart = fridayStart;
        this.fridayEnd = fridayEnd;
        this.saturdayStart = saturdayStart;
        this.saturdayEnd = saturdayEnd;
        this.sundayStart = sundayStart;
        this.sundayEnd = sundayEnd;
    }

    public WorkGraphic () {};

    public String getMondayEnd() {
        return mondayEnd;
    }

    public String getTuesdayStart() {
        return tuesdayStart;
    }

    public String getTuesdayEnd() {
        return tuesdayEnd;
    }

    public String getWednesdayStart() {
        return wednesdayStart;
    }

    public String getWednesdayEnd() {
        return wednesdayEnd;
    }

    public String getThursdayStart() {
        return thursdayStart;
    }

    public String getThursdayEnd() {
        return thursdayEnd;
    }

    public String getFridayStart() {
        return fridayStart;
    }

    public String getFridayEnd() {
        return fridayEnd;
    }

    public String getSaturdayStart() {
        return saturdayStart;
    }

    public String getSaturdayEnd() {
        return saturdayEnd;
    }

    public String getSundayStart() {
        return sundayStart;
    }

    public String getSundayEnd() {
        return sundayEnd;
    }

    public String getMondayStart() {
        return mondayStart;
    }


}
