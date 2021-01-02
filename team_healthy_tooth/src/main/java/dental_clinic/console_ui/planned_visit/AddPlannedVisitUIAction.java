package dental_clinic.console_ui.planned_visit;

import dental_clinic.console_ui.UIAction;
import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.plannedVisit.AddPlannedVisitRequest;
import dental_clinic.core.responses.planned_visit.AddPlannedVisitResponse;
import dental_clinic.core.services.planned_visit.AddPlannedVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddPlannedVisitUIAction implements UIAction {

    @Autowired
    private AddPlannedVisitService addPlannedVisitService;

    @Override
    public void execute() {

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter visit date in format dd-MM-yyyy HH:mm");
        String visitDate = in.nextLine();

        System.out.println("Please enter name");
        String name = in.nextLine();
        System.out.println("Please enter surname");
        String surname = in.nextLine();
        System.out.println("Please enter phone");
        String phone = in.nextLine();
        System.out.println("Please enter personal code");
        String personalCode = in.nextLine();

        PersonalData personalData = new PersonalData(name, surname, phone, personalCode);

        AddPlannedVisitRequest addPlannedVisitRequest = new AddPlannedVisitRequest(visitDate, personalData);
        AddPlannedVisitResponse addPlannedVisitResponse = addPlannedVisitService.execute(addPlannedVisitRequest);

        if (addPlannedVisitResponse.hasErrors()) {
            addPlannedVisitResponse.getErrors().forEach(System.out::println);
        } else {
            System.out.println("Visit added successfully:\n" +
               addPlannedVisitResponse.getPlannedVisit().toString());
        }
    }
}
