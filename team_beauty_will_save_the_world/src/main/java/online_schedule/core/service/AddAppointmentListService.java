package online_schedule.core.service;

public class AddAppointmentListService {

    private String Date;
    private String Time;
    private String Master;
    private String ClientId;

    public AddAppointmentListService() {
    }

    public AddAppointmentListService(String date, String time, String doctor_ID, String patientID) {
        this.Date = date;
        this.Time = time;
        this.Master = doctor_ID;
        this.ClientId = patientID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        this.Time = time;
    }

    public String getMaster() {
        return Master;
    }

    public void setMaster(String doctor_ID) {
        this.Master = Master;
    }

    public String getClientId() {
        return ClientId;
    }

    public void setClientId(String clientId) {
        this.ClientId = clientId;
    }
}
