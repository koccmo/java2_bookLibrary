package dental_clinic.console_ui.planned_visit;

import dental_clinic.console_ui.InputFormatsValidator;
import dental_clinic.console_ui.UIAction;
import dental_clinic.core.requests.plannedVisit.CancelPlannedVisitRequest;
import dental_clinic.core.responses.planned_visit.CancelPlannedVisitResponse;
import dental_clinic.core.services.planned_visit.CancelPlannedVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CancelPlannedVisitUIAction implements UIAction {

    @Autowired
    private CancelPlannedVisitService cancelPlannedVisitService;
    @Autowired
    private InputFormatsValidator inputFormatsValidator;

    @Override
    public void execute() {

        Long id = inputFormatsValidator.inputLong("Please enter id");

        CancelPlannedVisitRequest cancelPlannedVisitRequest = new CancelPlannedVisitRequest(id);
        CancelPlannedVisitResponse cancelPlannedVisitResponse = cancelPlannedVisitService.execute(cancelPlannedVisitRequest);

        if (cancelPlannedVisitResponse.hasErrors()) {
            cancelPlannedVisitResponse.getErrors().forEach(System.out::println);
        } else {
            System.out.println("Visit with id " + cancelPlannedVisitResponse.getId() + " is cancelled");
        }
    }
}
