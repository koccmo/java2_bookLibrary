package dental_clinic.core.requests.doctor;

public class FillDoctorsWorkGraphicRequest {

    private Long id;
    private Integer day;
    private String start;
    private String end;

    public FillDoctorsWorkGraphicRequest () { }

    public FillDoctorsWorkGraphicRequest(Long id, Integer day, String start, String end) {
        this.id = id;
        this.day = day;
        this.start = start;
        this.end = end;
    }

    public Long getId() {
        return id;
    }

    public Integer getDay() {
        return day;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
