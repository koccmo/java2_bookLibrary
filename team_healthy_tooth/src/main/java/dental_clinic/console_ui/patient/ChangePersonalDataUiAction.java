package dental_clinic.console_ui.patient;

import dental_clinic.console_ui.InputFormatsValidator;
import dental_clinic.console_ui.UIAction;
import dental_clinic.core.requests.patient.ChangePersonalDataRequest;
import dental_clinic.core.requests.ContainsDatabaseIdRequest;
import dental_clinic.core.responses.patient.ChangePersonalDataResponse;
import dental_clinic.core.responses.ContainsDatabaseIdResponse;
import dental_clinic.core.responses.CoreResponse;
import dental_clinic.core.services.patient.ChangePersonalDataService;
import dental_clinic.core.services.ContainsDatabaseIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ChangePersonalDataUiAction implements UIAction {

    @Autowired
    private ChangePersonalDataService service;
    @Autowired
    private ContainsDatabaseIdService containsDatabaseIdService;
    @Autowired
    private InputFormatsValidator inputFormatsValidator;

    public void execute() {
        Scanner in = new Scanner(System.in);

        Long id = inputFormatsValidator.inputLong("Enter patient's id number: ");

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
        ContainsDatabaseIdRequest containsDatabaseIdRequest = new ContainsDatabaseIdRequest(id);
        ContainsDatabaseIdResponse containsDatabaseIdResponse = containsDatabaseIdService.execute(containsDatabaseIdRequest);
        return containsDatabaseIdResponse;
    }

    private void printResult(String surname, String phone){
        if ((surname != null && !surname.isEmpty()) || (phone != null && !phone.isEmpty())) {
            System.out.println("Patient's personal data updated!");
        }else{
            System.out.println("No input for update");
        }
    }

}