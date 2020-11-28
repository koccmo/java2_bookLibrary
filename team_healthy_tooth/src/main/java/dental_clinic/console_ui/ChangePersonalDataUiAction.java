package dental_clinic.console_ui;

import dental_clinic.core.requests.ChangePersonalDataRequest;
import dental_clinic.core.requests.CheckPatientByIdRequest;
import dental_clinic.core.responses.ChangePersonalDataResponse;
import dental_clinic.core.responses.CheckPatientByIdResponse;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.responses.CoreResponse;
import dental_clinic.core.services.ChangePersonalDataService;
import dental_clinic.core.services.CheckPatientByIdService;

import java.util.List;
import java.util.Scanner;

public class ChangePersonalDataUiAction implements UIAction {

    private final ChangePersonalDataService service;
    private CheckPatientByIdService checkPatientByIdService;

    public ChangePersonalDataUiAction(ChangePersonalDataService service, CheckPatientByIdService checkPatientByIdService) {
        this.service = service;
        this.checkPatientByIdService = checkPatientByIdService;
    }

    public void execute() {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter patient's id number : ");
        long id = in.nextLong();
        in.nextLine();

        if (notExistingId(id).hasErrors()){
            notExistingId(id).getErrors().forEach(System.out::println);
        }else {
            System.out.println("Enter new surname : ");
            String surname = in.nextLine();

            System.out.println("Enter new phone number : ");
            String phone = in.nextLine();

            ChangePersonalDataRequest request = new ChangePersonalDataRequest(id, surname, phone);
            ChangePersonalDataResponse response = service.execute(request);

            if (response.hasErrors()) {
                response.getErrors().forEach(System.out::println);
            } else {
                printResult(surname, phone);
            }
        }
    }

    private CoreResponse notExistingId (Long id){
        CheckPatientByIdRequest checkPatientByIdRequest = new CheckPatientByIdRequest(id);
        CheckPatientByIdResponse checkPatientByIdResponse = checkPatientByIdService.execute( checkPatientByIdRequest );
        return checkPatientByIdResponse;
    }

    private void printResult(String surname, String phone){
        if (surname != null && !surname.isEmpty() && phone != null && !phone.isEmpty()) {
            System.out.println("Patient's personal data updated!");
        }else{
            System.out.println("No input for update");
        }
    }

}