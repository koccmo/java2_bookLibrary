package dental_clinic.core.responses.doctor;

import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;

import java.util.List;

public class FillDoctorsWorkGraphicResponse extends CoreResponse {

    private Long id;
    private Integer day;
    private String timeFrom;
    private String timeTo;

    public FillDoctorsWorkGraphicResponse(Long id, Integer day, String timeFrom, String timeTo) {
        this.id = id;
        this.day = day;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }

    public FillDoctorsWorkGraphicResponse(List<CoreError> errors) {
        super(errors);
    }

    public Long getId() {
        return id;
    }

    public Integer getDay() {
        return day;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }
}
