package dental_clinic.console_ui;

import dental_clinic.core.requests.ChangePersonalDataRequest;
import dental_clinic.core.requests.ContainsDatabaseIdRequest;
import dental_clinic.core.responses.ChangePersonalDataResponse;
import dental_clinic.core.responses.ContainsDatabaseIdResponse;
import dental_clinic.core.responses.CoreResponse;
import dental_clinic.core.services.ChangePersonalDataService;
import dental_clinic.core.services.ContainsDatabaseIdService;
import dental_clinic.dependency_injection.DIComponent;
import dental_clinic.dependency_injection.DIDependency;

import java.util.Scanner;

@DIComponent
public class ChangePersonalDataUiAction implements UIAction {

    @DIDependency private ChangePersonalDataService service;
    @DIDependency private ContainsDatabaseIdService containsDatabaseIdService;

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