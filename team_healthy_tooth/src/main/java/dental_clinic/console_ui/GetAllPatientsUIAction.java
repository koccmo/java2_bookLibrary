package dental_clinic.console_ui;

import dental_clinic.core.requests.GetAllPatientsRequest;
import dental_clinic.core.responses.GetAllPatientsResponse;
import dental_clinic.core.services.GetAllPatientsService;
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

