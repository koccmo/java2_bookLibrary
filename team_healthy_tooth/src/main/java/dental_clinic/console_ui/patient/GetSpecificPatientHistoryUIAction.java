package dental_clinic.console_ui.patient;

import dental_clinic.console_ui.InputFormatsValidator;
import dental_clinic.console_ui.UIAction;
import dental_clinic.core.requests.patient.GetSpecificPatientHistoryRequest;
import dental_clinic.core.responses.patient.GetSpecificPatientHistoryResponse;
import dental_clinic.core.services.patient.GetSpecificPatientHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class GetSpecificPatientHistoryUIAction implements UIAction {

    @Autowired
    private GetSpecificPatientHistoryService printSpecificPatientHistory;
    @Autowired
    private InputFormatsValidator inputFormatsValidator;

    public void execute(){
        Long id = inputFormatsValidator.inputLong("Please enter patient id");

        GetSpecificPatientHistoryRequest getSpecificPatientHistoryRequest = new GetSpecificPatientHistoryRequest(id);
        GetSpecificPatientHistoryResponse getSpecificPatientHistoryResponse = printSpecificPatientHistory.execute(getSpecificPatientHistoryRequest);

        if (getSpecificPatientHistoryResponse.hasErrors()){
            getSpecificPatientHistoryResponse.getErrors().forEach(System.out::println);
        }else{
            System.out.println(getSpecificPatientHistoryResponse.getSpecificPatient().toString());
        }
    }

}

