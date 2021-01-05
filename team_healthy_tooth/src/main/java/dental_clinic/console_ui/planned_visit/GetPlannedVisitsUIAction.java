package dental_clinic.console_ui.planned_visit;

import dental_clinic.console_ui.UIAction;
import dental_clinic.core.requests.plannedVisit.GetPlannedVisitsRequest;
import dental_clinic.core.responses.planned_visit.GetPlannedVisitsResponse;
import dental_clinic.core.services.planned_visit.GetPlannedVisitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetPlannedVisitsUIAction implements UIAction {

    @Autowired
    private GetPlannedVisitsService getPlannedVisitsService;

    @Override
    public void execute() {

        GetPlannedVisitsRequest getPlannedVisitsRequest = new GetPlannedVisitsRequest();
        GetPlannedVisitsResponse getPlannedVisitsResponse = getPlannedVisitsService.execute(getPlannedVisitsRequest);

        if (getPlannedVisitsResponse.hasErrors()) {
            getPlannedVisitsResponse.getErrors().forEach(System.out::println);
        } else {
            System.out.println("Visits:");
            getPlannedVisitsResponse.getPlannedVisits().forEach(System.out::println);
        }

    }
}
