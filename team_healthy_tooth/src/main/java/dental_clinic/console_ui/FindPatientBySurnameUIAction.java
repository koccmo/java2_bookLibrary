package dental_clinic.console_ui;

import dental_clinic.core.domain.Patient;
import dental_clinic.core.requests.FindPatientBySurnameRequest;
import dental_clinic.core.responses.FindPatientBySurnameResponse;
import dental_clinic.core.services.FindPatientsBySurnameService;

import java.util.List;
import java.util.Scanner;

class FindPatientBySurnameUIAction implements UIAction {

    private FindPatientsBySurnameService findPatientBySurname;

    public FindPatientBySurnameUIAction(FindPatientsBySurnameService findPatientBySurname) {
        this.findPatientBySurname = findPatientBySurname;
    }

    public void execute(){
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter surname");
        String surname = in.nextLine();

        FindPatientBySurnameRequest findPatientBySurnameRequest = new FindPatientBySurnameRequest(surname);
        FindPatientBySurnameResponse findPatientBySurnameResponse = findPatientBySurname.execute(findPatientBySurnameRequest);

        if (findPatientBySurnameResponse.hasErrors()){
            findPatientBySurnameResponse.getErrors().forEach(System.out::println);
        }else{
            findPatientBySurnameResponse.getSpecificPatient().forEach(System.out::println);
        }

    }

}

