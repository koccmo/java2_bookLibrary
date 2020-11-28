package dental_clinic.console_ui;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.requests.FindPatientByPersonalCodeRequest;
import dental_clinic.core.responses.FindPatientByPersonalCodeResponse;
import dental_clinic.core.services.FindPatientsByPersonalCodeService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

class FindPatientByPersonalCodeUIAction implements UIAction {

    private FindPatientsByPersonalCodeService findPatientByPersonalCode;


    public FindPatientByPersonalCodeUIAction(FindPatientsByPersonalCodeService findPatientByPersonalCode) {
        this.findPatientByPersonalCode = findPatientByPersonalCode;
    }

    public void execute(){
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter personal code");
        String personalCode = in.nextLine();

        FindPatientByPersonalCodeRequest findPatientByPersonalCodeRequest = new FindPatientByPersonalCodeRequest(personalCode);
        FindPatientByPersonalCodeResponse findPatientByPersonalCodeResponse = findPatientByPersonalCode.execute(findPatientByPersonalCodeRequest);

        if (findPatientByPersonalCodeResponse.hasErrors()){
            findPatientByPersonalCodeResponse.getErrors().forEach(System.out::println);
        }else{
            System.out.println("Specific patient: " + findPatientByPersonalCodeResponse.getFoundPatient());
        }
    }

}

