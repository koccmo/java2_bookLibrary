package dental_clinic.console_ui.planned_visit;

import dental_clinic.console_ui.UIAction;
import dental_clinic.core.requests.plannedVisit.SearchPlannedVisitsByPersonalCodeRequest;
import dental_clinic.core.responses.planned_visit.SearchPlannedVisitsByPersonalCodeResponse;
import dental_clinic.core.services.planned_visit.SearchPlannedVisitsByPersonalCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SearchPlannedVisitsByPersonalCodeUIAction implements UIAction {

    @Autowired
    private SearchPlannedVisitsByPersonalCodeService searchPlannedVisitsByPersonalCodeService;

    public void execute () {

        Scanner in = new Scanner(System.in);

        System.out.println("Please input personal code in format: DDMMYYYNNNNN");
        String personalCode = in.nextLine();

        SearchPlannedVisitsByPersonalCodeRequest searchPlannedVisitsByPersonalCodeRequest = new SearchPlannedVisitsByPersonalCodeRequest(personalCode);
        SearchPlannedVisitsByPersonalCodeResponse searchPlannedVisitsByPersonalCodeResponse = searchPlannedVisitsByPersonalCodeService.execute(searchPlannedVisitsByPersonalCodeRequest);

        if (searchPlannedVisitsByPersonalCodeResponse.hasErrors()) {
            searchPlannedVisitsByPersonalCodeResponse.getErrors().forEach(System.out::println);
        } else {
            System.out.println("Search result:");
            searchPlannedVisitsByPersonalCodeResponse.getPlannedVisitList().forEach(System.out::println);
        }
    }
}
