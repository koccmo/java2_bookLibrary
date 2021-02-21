package dental_clinic.console_ui.planned_visit;

import dental_clinic.console_ui.InputFormatsValidator;
import dental_clinic.console_ui.UIAction;
import dental_clinic.core.domain.OrderingDirection;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.Ordering;
import dental_clinic.core.requests.Paging;
import dental_clinic.core.requests.doctor.GetDoctorListRequest;
import dental_clinic.core.requests.patient.SearchPatientRequest;
import dental_clinic.core.requests.plannedVisit.AddPlannedVisitRequest;
import dental_clinic.core.responses.doctor.GetDoctorListResponse;
import dental_clinic.core.responses.patient.SearchPatientResponse;
import dental_clinic.core.responses.planned_visit.AddPlannedVisitResponse;
import dental_clinic.core.services.doctor.GetDoctorListService;
import dental_clinic.core.services.patient.SearchPatientService;
import dental_clinic.core.services.planned_visit.AddPlannedVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddPlannedVisitUIAction implements UIAction {

    @Autowired
    private AddPlannedVisitService addPlannedVisitService;
    @Autowired
    private SearchPatientService searchPatientService;
    @Autowired
    private GetDoctorListService getDoctorListService;
    @Autowired
    private InputFormatsValidator inputFormatsValidator;

    @Override
    public void execute() {

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter visit date in format DD-MM-YYYY HH:MM");
        String visitDate = in.nextLine();

        System.out.println("Please enter personal code");
        String personalCode = in.nextLine();

        GetDoctorListRequest getDoctorListRequest = new GetDoctorListRequest();
        GetDoctorListResponse getDoctorListResponse = getDoctorListService.execute(getDoctorListRequest);

        if (getDoctorListResponse.hasErrors()) {
            System.out.println("Planned visit canned be registered");
        } else {
            getDoctorListResponse.getDoctorAndGraphic().forEach(System.out::println);

            Long id = inputFormatsValidator.inputLong("Please input doctor's id");

            AddPlannedVisitRequest addPlannedVisitRequest = new AddPlannedVisitRequest(visitDate, personalCode, id);
            AddPlannedVisitResponse addPlannedVisitResponse = addPlannedVisitService.execute(addPlannedVisitRequest);

            if (addPlannedVisitResponse.hasErrors()) {
                addPlannedVisitResponse.getErrors().forEach(System.out::println);
            } else {
                System.out.println("Visit added successfully:\n" +
                        addPlannedVisitResponse.getPlannedVisit());
            }
        }
    }
}
