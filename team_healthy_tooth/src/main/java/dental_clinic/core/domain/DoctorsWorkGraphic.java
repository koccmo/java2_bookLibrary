package dental_clinic.core.domain;

import javax.persistence.*;

@Entity
@Table(name="doctorsWorkGraphic")
public class DoctorsWorkGraphic {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name= "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(name="monday_start")
    private String monday_start = "-";

    @Column(name = "monday_end")
    private String monday_end = "-";

    @Column(name="tuesday_start")
    private String tuesday_start = "-";

    @Column(name = "tuesday_end")
    private String tuesday_end = "-";

    @Column(name="wednesday_start")
    private String wednesday_start = "-";

    @Column(name = "wednesday_end")
    private String wednesday_end = "-";

    @Column(name="thursday_start")
    private String thursday_start = "-";

    @Column(name = "thursday_end")
    private String thursday_end = "-";

    @Column(name="friday_start")
    private String friday_start = "-";

    @Column(name = "friday_end")
    private String friday_end = "-";

    @Column(name="saturday_start")
    private String saturday_start = "-";

    @Column(name = "saturday_end")
    private String saturday_end = "-";

    @Column(name="sunday_start")
    private String sunday_start = "-";

    @Column(name = "sunday_end")
    private String sunday_end = "-";

    public DoctorsWorkGraphic() { }

    public DoctorsWorkGraphic(Doctor doctor) {
        this.doctor = doctor;
    }

    public String[] getTimesStart() {
        return getTimeStart();
    }

    public String[] getTimesEnd() {
        return getTimeEnd();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getMonday_start() {
        return monday_start;
    }

    public void setMonday_start(String monday_start) {
        this.monday_start = monday_start;
    }

    public String getMonday_end() {
        return monday_end;
    }

    public void setMonday_end(String monday_end) {
        this.monday_end = monday_end;
    }

    public String getTuesday_start() {
        return tuesday_start;
    }

    public void setTuesday_start(String tuesday_start) {
        this.tuesday_start = tuesday_start;
    }

    public String getTuesday_end() {
        return tuesday_end;
    }

    public void setTuesday_end(String tuesday_end) {
        this.tuesday_end = tuesday_end;
    }

    public String getWednesday_start() {
        return wednesday_start;
    }

    public void setWednesday_start(String wednesday_start) {
        this.wednesday_start = wednesday_start;
    }

    public String getWednesday_end() {
        return wednesday_end;
    }

    public void setWednesday_end(String wednesday_end) {
        this.wednesday_end = wednesday_end;
    }

    public String getThursday_start() {
        return thursday_start;
    }

    public void setThursday_start(String thursday_start) {
        this.thursday_start = thursday_start;
    }

    public String getThursday_end() {
        return thursday_end;
    }

    public void setThursday_end(String thursday_end) {
        this.thursday_end = thursday_end;
    }

    public String getFriday_start() {
        return friday_start;
    }

    public void setFriday_start(String friday_start) {
        this.friday_start = friday_start;
    }

    public String getFriday_end() {
        return friday_end;
    }

    public void setFriday_end(String friday_end) {
        this.friday_end = friday_end;
    }

    public String getSaturday_start() {
        return saturday_start;
    }

    public void setSaturday_start(String saturday_start) {
        this.saturday_start = saturday_start;
    }

    public String getSaturday_end() {
        return saturday_end;
    }

    public void setSaturday_end(String saturday_end) {
        this.saturday_end = saturday_end;
    }

    public String getSunday_start() {
        return sunday_start;
    }

    public void setSunday_start(String sunday_start) {
        this.sunday_start = sunday_start;
    }

    public String getSunday_end() {
        return sunday_end;
    }

    public void setSunday_end(String sunday_end) {
        this.sunday_end = sunday_end;
    }

    private String [] getTimeStart() {
        String timesStart [] = new String [7];
        timesStart[0] = monday_start;
        timesStart[1] = tuesday_start;
        timesStart[2] = wednesday_start;
        timesStart[3] = thursday_start;
        timesStart[4] = friday_start;
        timesStart[5] = saturday_start;
        timesStart[6] = sunday_start;
        return timesStart;
    }

    private String [] getTimeEnd() {
        String timesEnd [] = new String [7];
        timesEnd[0] = monday_end;
        timesEnd[1] = tuesday_end;
        timesEnd[2] = wednesday_end;
        timesEnd[3] = thursday_end;
        timesEnd[4] = friday_end;
        timesEnd[5] = saturday_end;
        timesEnd[6] = sunday_end;
        return timesEnd;
    }

    @Override
    public String toString() {
        return "DoctorsWorkGraphic:" +
                "\nmonday: " + monday_start + " : " + monday_end +
                "\ntuesday: " + tuesday_start + " : " + tuesday_end +
                "\nwednesday: " + wednesday_start + " : " + wednesday_end +
                "\nthursday: " + thursday_start + " : " + thursday_end +
                "\nfriday: " + friday_start + " : " + friday_end +
                "\nsaturday: " + saturday_start + " : " + saturday_end +
                "\nsunday: " + sunday_start + " : " + sunday_end + "\n\n";
    }
}
