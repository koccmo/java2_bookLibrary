package dental_clinic.console_ui.patient;

import dental_clinic.console_ui.UIAction;
import dental_clinic.core.requests.patient.SearchPatientByPersonalCodeRequest;
import dental_clinic.core.responses.patient.SearchPatientByPersonalCodeResponse;
import dental_clinic.core.services.patient.SearchPatientsByPersonalCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SearchPatientByPersonalCodeUIAction implements UIAction {

    @Autowired
    private SearchPatientsByPersonalCodeService findPatientByPersonalCode;

    public void execute(){
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter personal code");
        String personalCode = in.nextLine();

        SearchPatientByPersonalCodeRequest searchPatientByPersonalCodeRequest = new SearchPatientByPersonalCodeRequest(personalCode);
        SearchPatientByPersonalCodeResponse searchPatientByPersonalCodeResponse = findPatientByPersonalCode.execute(searchPatientByPersonalCodeRequest);

        if (searchPatientByPersonalCodeResponse.hasErrors()){
            searchPatientByPersonalCodeResponse.getErrors().forEach(System.out::println);
        }else{
            System.out.println("Specific patient: " + searchPatientByPersonalCodeResponse.getFoundPatient());
        }
    }

}

