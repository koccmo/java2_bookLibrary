package dental_clinic.core.requests;

public class FindPatientBySurnameRequest {

    private String surname;

    public FindPatientBySurnameRequest (String surname){
        this.surname = surname;
    }

    public String getSurname(){
        return surname;
    }
}
