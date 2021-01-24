package dental_clinic.console_ui.planned_visit;

import dental_clinic.console_ui.InputFormatsValidator;
import dental_clinic.console_ui.UIAction;
import dental_clinic.core.requests.plannedVisit.SearchPlannedVisitsByDateRequest;
import dental_clinic.core.responses.planned_visit.SearchPlannedVisitsByDateResponse;
import dental_clinic.core.services.planned_visit.SearchPlannedVisitsByDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SearchPlannedVisitsByDateUIAction implements UIAction {

    @Autowired
    private SearchPlannedVisitsByDateService searchPlannedVisitsByDateService;
    @Autowired
    private InputFormatsValidator inputFormatsValidator;

    @Override
    public void execute() {
        System.out.println("Search is provided in format DD MM to DD MM");
        int dayFrom = inputFormatsValidator.inputInteger("Please input day from");
        int monthFrom = inputFormatsValidator.inputInteger("Please input month from");
        int dayTo = inputFormatsValidator.inputInteger("Please input day to");
        int monthTo = inputFormatsValidator.inputInteger("Please input month to");

        SearchPlannedVisitsByDateRequest searchPlannedVisitsByDateRequest = new SearchPlannedVisitsByDateRequest(dayFrom, dayTo, monthFrom, monthTo);
        SearchPlannedVisitsByDateResponse searchPlannedVisitsByDateResponse = searchPlannedVisitsByDateService.execute(searchPlannedVisitsByDateRequest);

        if (searchPlannedVisitsByDateResponse.hasErrors()) {
            searchPlannedVisitsByDateResponse.getErrors().forEach(System.out::println);
        } else {
            System.out.println("Search result:");
            searchPlannedVisitsByDateResponse.getPlannedVisitList().forEach(System.out::println);
        }
    }
}
