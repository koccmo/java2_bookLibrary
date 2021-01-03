package dental_clinic.console_ui.planned_visit;

import dental_clinic.console_ui.UIAction;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.patient.SearchPatientByPersonalCodeRequest;
import dental_clinic.core.requests.plannedVisit.AddPlannedVisitRequest;
import dental_clinic.core.responses.patient.SearchPatientByPersonalCodeResponse;
import dental_clinic.core.responses.planned_visit.AddPlannedVisitResponse;
import dental_clinic.core.services.patient.SearchPatientsByPersonalCodeService;
import dental_clinic.core.services.planned_visit.AddPlannedVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddPlannedVisitUIAction implements UIAction {

    @Autowired
    private AddPlannedVisitService addPlannedVisitService;
    @Autowired
    private SearchPatientsByPersonalCodeService searchPatientsByPersonalCodeService;

    @Override
    public void execute() {

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter visit date in format DD-MM-YYYY HH:MM");
        String visitDate = in.nextLine();

        System.out.println("Please enter personal code");
        String personalCode = in.nextLine();

        SearchPatientByPersonalCodeRequest searchPatientByPersonalCodeRequest = new SearchPatientByPersonalCodeRequest(personalCode);
        SearchPatientByPersonalCodeResponse searchPatientByPersonalCodeResponse = searchPatientsByPersonalCodeService.execute(searchPatientByPersonalCodeRequest);

        PersonalData personalData = new PersonalData(null, null, null, personalCode);
        Boolean isNewPatient = false;

        if (searchPatientByPersonalCodeResponse.hasErrors()) {
            System.out.println("Please enter name");
            String name = in.nextLine();
            System.out.println("Please enter surname");
            String surname = in.nextLine();
            System.out.println("Please enter phone");
            String phone = in.nextLine();

            personalData = new PersonalData(name, surname, phone, personalCode);
            isNewPatient = true;
        }

        AddPlannedVisitRequest addPlannedVisitRequest = new AddPlannedVisitRequest(isNewPatient, visitDate, personalData);
        AddPlannedVisitResponse addPlannedVisitResponse = addPlannedVisitService.execute(addPlannedVisitRequest);

        if (addPlannedVisitResponse.hasErrors()) {
            addPlannedVisitResponse.getErrors().forEach(System.out::println);
        } else {
            System.out.println("Visit added successfully:\n" +
               addPlannedVisitResponse.getPlannedVisit());
        }
    }
}
