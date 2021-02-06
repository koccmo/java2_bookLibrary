package dental_clinic.console_ui.visit;

import dental_clinic.console_ui.InputFormatsValidator;
import dental_clinic.console_ui.UIAction;
import dental_clinic.core.requests.visit.SearchVisitByDateRequest;
import dental_clinic.core.responses.visit.SearchVisitByDateResponse;
import dental_clinic.core.services.visit.SearchVisitByDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchVisitByDateUIAction implements UIAction {

    @Autowired
    private SearchVisitByDateService searchVisitByDateService;
    @Autowired
    private InputFormatsValidator inputFormatsValidator;

    @Override
    public void execute() {
        System.out.println("Search is provided in format DD MM to DD MM");
        int dayFrom = inputFormatsValidator.inputInteger("Please input day from");
        int monthFrom = inputFormatsValidator.inputInteger("Please input month from");
        int dayTo = inputFormatsValidator.inputInteger("Please input day to");
        int monthTo = inputFormatsValidator.inputInteger("Please input month to");

        SearchVisitByDateRequest searchVisitByDateRequest = new SearchVisitByDateRequest(dayFrom, dayTo, monthFrom, monthTo);
        SearchVisitByDateResponse searchVisitByDateResponse = searchVisitByDateService.execute(searchVisitByDateRequest);

        if (searchVisitByDateResponse.hasErrors()) {
            searchVisitByDateResponse.getErrors().forEach(System.out::println);
        } else {
            System.out.println("Search result:");
            searchVisitByDateResponse.getVisitList().forEach(System.out::println);
        }
    }
}
