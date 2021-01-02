package dental_clinic.console_ui.planned_visit;

import dental_clinic.console_ui.InputFormatsValidator;
import dental_clinic.console_ui.UIAction;
import dental_clinic.core.requests.plannedVisit.ChangePlannedVisitTimeRequest;
import dental_clinic.core.responses.planned_visit.ChangePlannedVisitTimeResponse;
import dental_clinic.core.services.planned_visit.ChangePlannedVisitTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Scanner;

@Component
public class ChangePlannedVisitTimeUIAction implements UIAction {

    @Autowired
    private ChangePlannedVisitTimeService changePlannedVisitTimeService;
    @Autowired
    private InputFormatsValidator inputFormatsValidator;

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        Long id = inputFormatsValidator.inputLong("Please enter planned visit id");

        System.out.println("Please enter visit date in format dd-MM-yyyy HH:mm");
        String visitDate = in.nextLine();

        ChangePlannedVisitTimeRequest changePlannedVisitTimeRequest = new ChangePlannedVisitTimeRequest(id, visitDate);
        ChangePlannedVisitTimeResponse changePlannedVisitTimeResponse =
                changePlannedVisitTimeService.execute(changePlannedVisitTimeRequest);

        if (changePlannedVisitTimeResponse.hasErrors()) {
            changePlannedVisitTimeResponse.getErrors().forEach(System.out::println);
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-YYYY HH:mm");
            System.out.println("Visit with id " + changePlannedVisitTimeResponse.getId() + " is changed,\n" +
                    "visit time: " + simpleDateFormat.format(changePlannedVisitTimeResponse.getVisitTime().getTime()));
        }
    }
}
