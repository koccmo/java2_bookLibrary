package dental_clinic.console_ui.patient;

import dental_clinic.console_ui.InputFormatsValidator;
import dental_clinic.console_ui.UIAction;
import dental_clinic.core.requests.patient.DeletePatientRequest;
import dental_clinic.core.responses.patient.DeletePatientResponse;
import dental_clinic.core.services.patient.DeletePatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeletePatientUIAction implements UIAction {

    @Autowired
    private DeletePatientService deletePatientService;
    @Autowired
    private InputFormatsValidator inputFormatsValidator;

    public void execute(){

        Long id = inputFormatsValidator.inputLong("Please enter patient's id");

        DeletePatientRequest deletePatientRequest = new DeletePatientRequest(id);
        DeletePatientResponse deletePatientResponse = deletePatientService.execute(deletePatientRequest);

        if (deletePatientResponse.hasErrors()){
            deletePatientResponse.getErrors().forEach(System.out::println);
        }else{
            System.out.println("Patient with id " + id + " is deleted");
        }
    }

}

