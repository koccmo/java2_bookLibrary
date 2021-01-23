package dental_clinic.console_ui.visit;

import dental_clinic.console_ui.InputFormatsValidator;
import dental_clinic.console_ui.UIAction;
import dental_clinic.core.requests.visit.SearchVisitByPatientIdRequest;
import dental_clinic.core.responses.visit.SearchVisitByPatientIdResponse;
import dental_clinic.core.services.visit.SearchVisitByPatientIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchVisitByPatientIdUIAction implements UIAction {

    @Autowired
    private SearchVisitByPatientIdService searchVisitByPatientIdService;
    @Autowired
    private InputFormatsValidator inputFormatsValidator;

    @Override
    public void execute() {
        Long id = inputFormatsValidator.inputLong("Please enter patient's id to search visits");

        SearchVisitByPatientIdRequest searchVisitByPatientIdRequest = new SearchVisitByPatientIdRequest(id);
        SearchVisitByPatientIdResponse searchVisitByPatientIdResponse = searchVisitByPatientIdService.execute(searchVisitByPatientIdRequest);

        if (searchVisitByPatientIdResponse.hasErrors()) {
            searchVisitByPatientIdResponse.getErrors().forEach(System.out::println);
        } else {
            searchVisitByPatientIdResponse.getVisitList().forEach(System.out::println);
        }
    }
}
