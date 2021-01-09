package dental_clinic.console_ui.patient;

import dental_clinic.console_ui.InputFormatsValidator;
import dental_clinic.console_ui.UIAction;
import dental_clinic.core.requests.patient.GetPatientCardRequest;
import dental_clinic.core.responses.patient.GetPatientCardResponse;
import dental_clinic.core.services.patient.GetPatientCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class GetPatientCardUIAction implements UIAction {

    @Autowired
    private GetPatientCardService getPatientCardService;
    @Autowired
    private InputFormatsValidator inputFormatsValidator;

    @Override
    public void execute(){

        Long id = inputFormatsValidator.inputLong("Please enter patient id");

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
