package dental_clinic.console_ui;

import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.AddPatientRequest;
import dental_clinic.core.responses.AddPatientResponse;
import dental_clinic.core.services.AddPatientService;
import dental_clinic.dependency_injection.DIComponent;
import dental_clinic.dependency_injection.DIDependency;

import java.util.Scanner;

@DIComponent
public class AddPatientUIAction implements UIAction {

    @DIDependency private AddPatientService addPatientService;

    public void execute(){
        Scanner in  = new Scanner(System.in);
        System.out.println("Please enter name");
        String name = in.nextLine();
        System.out.println("Please enter surname");
        String surname = in.nextLine();
        System.out.println("Please enter phone");
        String phone = in.nextLine();
        System.out.println("Please enter personal code");
        String personalCode = in.nextLine();

        PersonalData personalData = new PersonalData(name, surname, phone, personalCode);

        AddPatientRequest addPatientRequest = new AddPatientRequest(personalData);
        AddPatientResponse response = addPatientService.execute(addPatientRequest);

        if (response.hasErrors()){
            response.getErrors().forEach(System.out::println);
        }else{
            System.out.println("Patient added");
        }

    }
}
