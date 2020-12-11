package dental_clinic.console_ui;

import dental_clinic.core.requests.GetPatientCardRequest;
import dental_clinic.core.responses.GetPatientCardResponse;
import dental_clinic.core.services.GetPatientCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class GetPatientCardUIAction implements UIAction{

    @Autowired
    private GetPatientCardService getPatientCardService;

    @Override
    public void execute(){

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter patient id");
        Long id = in.nextLong();

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
