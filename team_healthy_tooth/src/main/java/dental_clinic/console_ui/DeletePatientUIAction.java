package dental_clinic.console_ui;

import dental_clinic.core.requests.DeletePatientRequest;
import dental_clinic.core.responses.DeletePatientResponse;
import dental_clinic.core.services.DeletePatientService;
import dental_clinic.dependency_injection.DIComponent;
import dental_clinic.dependency_injection.DIDependency;

import java.util.Scanner;

@DIComponent
public class DeletePatientUIAction implements UIAction {

    @DIDependency private DeletePatientService deletePatientService;

    public void execute(){

        Scanner in = new Scanner(System.in);
        System.out.println("Please enter patients id");
        Long id = in.nextLong();

        DeletePatientRequest deletePatientRequest = new DeletePatientRequest(id);
        DeletePatientResponse deletePatientResponse = deletePatientService.execute(deletePatientRequest);

        if (deletePatientResponse.hasErrors()){
            deletePatientResponse.getErrors().forEach(System.out::println);
        }else{
            System.out.println("Patient with id " + id + " is deleted");
        }
    }

}

