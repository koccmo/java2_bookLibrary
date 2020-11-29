package dental_clinic.console_ui;

import dental_clinic.core.requests.ContainsDatabaseIdRequest;
import dental_clinic.core.responses.ContainsDatabaseIdResponse;
import dental_clinic.core.services.ContainsDatabaseIdService;

import java.util.Scanner;

class ContainsDatabaseIdUIAction implements UIAction {

    private ContainsDatabaseIdService containsDatabaseIdService;

    public ContainsDatabaseIdUIAction(ContainsDatabaseIdService checkPatientById) {
        this.containsDatabaseIdService = checkPatientById;
    }

    public void execute(){

        Scanner in = new Scanner(System.in);
        System.out.println("Please enter patients id");
        long id = in.nextLong();

        ContainsDatabaseIdRequest containsDatabaseIdRequest = new ContainsDatabaseIdRequest(id);
        ContainsDatabaseIdResponse containsDatabaseIdResponse = containsDatabaseIdService.execute(containsDatabaseIdRequest);

        if (containsDatabaseIdResponse.hasErrors()){
            containsDatabaseIdResponse.getErrors().forEach(System.out::println);
        }else{
            System.out.println("Patient with id " + id + " is not found");
        }
    }

}

