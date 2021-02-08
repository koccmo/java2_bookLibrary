package dental_clinic.console_ui.doctor;

import dental_clinic.console_ui.UIAction;
import dental_clinic.core.requests.doctor.GetDoctorListRequest;
import dental_clinic.core.responses.doctor.GetDoctorListResponse;
import dental_clinic.core.services.doctor.GetDoctorListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetDoctorListUIAction implements UIAction {

    @Autowired
    private GetDoctorListService getDoctorListService;


    @Override
    public void execute() {

        GetDoctorListRequest getDoctorListRequest = new GetDoctorListRequest();
        GetDoctorListResponse getDoctorListResponse = getDoctorListService.execute(getDoctorListRequest);

        if (getDoctorListResponse.hasErrors()) {
            getDoctorListResponse.getErrors().forEach(System.out::println);
        } else {
            getDoctorListResponse.getDoctorAndGraphic().forEach(System.out::println);
        }
    }
}
