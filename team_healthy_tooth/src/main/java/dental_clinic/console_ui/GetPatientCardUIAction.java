package dental_clinic.console_ui;

import dental_clinic.core.requests.GetPatientCardRequest;
import dental_clinic.core.responses.GetPatientCardResponse;
import dental_clinic.core.services.GetPatientCardService;

import java.util.Scanner;

public class GetPatientCardUIAction implements UIAction{

    private final GetPatientCardService getPatientCardService;

    public GetPatientCardUIAction(GetPatientCardService getPatientCardService) {
        this.getPatientCardService = getPatientCardService;
    }

    @Override
    public void execute(){

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter patient id");
        long id = in.nextLong();

        GetPatientCardRequest getPatientCardRequest = new GetPatientCardRequest(id);
        GetPatientCardResponse getPatientCardResponse = getPatientCardService.execute(getPatientCardRequest);

        if (getPatientCardResponse.hasErrors()){
            getPatientCardResponse.getErrors().forEach(System.out::println);
        }else{
            System.out.println("Patients card");
            System.out.println(getPatientCardResponse.getPatient().getPersonalData());
            System.out.println(getPatientCardResponse.getPatient().getJowl());
        }
    }
}
