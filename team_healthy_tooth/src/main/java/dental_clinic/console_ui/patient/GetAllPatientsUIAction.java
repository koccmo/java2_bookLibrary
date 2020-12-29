package dental_clinic.console_ui.patient;

import dental_clinic.console_ui.UIAction;
import dental_clinic.core.requests.patient.GetAllPatientsRequest;
import dental_clinic.core.responses.patient.GetAllPatientsResponse;
import dental_clinic.core.services.patient.GetAllPatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllPatientsUIAction implements UIAction {

    @Autowired
    private GetAllPatientsService getAllPatientsService;

    public void execute(){

        GetAllPatientsRequest getAllPatientsRequest = new GetAllPatientsRequest();
        GetAllPatientsResponse getAllPatientsResponse = getAllPatientsService.execute(getAllPatientsRequest);

        if (getAllPatientsResponse.hasErrors()){
            getAllPatientsResponse.getErrors().forEach(System.out::println);
        }else{
            getAllPatientsResponse.getPatients().forEach(System.out::println);
        }

    }

}

