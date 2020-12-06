package dental_clinic.console_ui;

import dental_clinic.core.requests.SearchPatientByPersonalCodeRequest;
import dental_clinic.core.responses.SearchPatientByPersonalCodeResponse;
import dental_clinic.core.services.SearchPatientsByPersonalCodeService;
import dental_clinic.dependency_injection.DIComponent;
import dental_clinic.dependency_injection.DIDependency;

import java.util.Scanner;

@DIComponent
public class SearchPatientByPersonalCodeUIAction implements UIAction {

    @DIDependency private SearchPatientsByPersonalCodeService findPatientByPersonalCode;

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

