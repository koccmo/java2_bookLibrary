package dental_clinic.console_ui;

import dental_clinic.core.requests.DeletePatientRequest;
import dental_clinic.core.responses.DeletePatientResponse;
import dental_clinic.core.services.DeletePatientService;

import java.util.Scanner;

class DeletePatientUIAction implements UIAction {

    private DeletePatientService deletePatientService;

    public DeletePatientUIAction(DeletePatientService deletePatientService) {
        this.deletePatientService = deletePatientService;
    }

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

