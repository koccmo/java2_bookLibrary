package dental_clinic.console_ui.doctor;

import dental_clinic.console_ui.InputFormatsValidator;
import dental_clinic.console_ui.UIAction;
import dental_clinic.core.requests.doctor.DeleteDoctorRequest;
import dental_clinic.core.responses.doctor.DeleteDoctorResponse;
import dental_clinic.core.services.doctor.DeleteDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteDoctorUIAction implements UIAction {

    @Autowired
    private DeleteDoctorService deleteDoctorService;
    @Autowired
    private InputFormatsValidator inputFormatsValidator;

    public void execute() {

        Long id = inputFormatsValidator.inputLong("Please enter doctor's id to delete");

        DeleteDoctorRequest deleteDoctorRequest = new DeleteDoctorRequest(id);
        DeleteDoctorResponse deleteDoctorResponse = deleteDoctorService.execute(deleteDoctorRequest);

        if (deleteDoctorResponse.hasErrors()) {
            deleteDoctorResponse.getErrors().forEach(System.out::println);
        } else {
            System.out.println("Doctor with id " + deleteDoctorRequest.getId() + " is deleted");
        }
    }

}
