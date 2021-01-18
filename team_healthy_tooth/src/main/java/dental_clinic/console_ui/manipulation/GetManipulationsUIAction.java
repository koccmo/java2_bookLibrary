package dental_clinic.console_ui.manipulation;

import dental_clinic.console_ui.UIAction;
import dental_clinic.core.requests.manipulation.GetManipulationsListRequest;
import dental_clinic.core.responses.manipulation.GetManipulationsListResponse;
import dental_clinic.core.services.manipulation.GetManipulationsListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetManipulationsUIAction implements UIAction {

    @Autowired
    private GetManipulationsListService getManipulationsListService;

    @Override
    public void execute() {

        GetManipulationsListRequest getManipulationsListRequest = new GetManipulationsListRequest();
        GetManipulationsListResponse getManipulationsListResponse = getManipulationsListService.execute(getManipulationsListRequest);

        if (getManipulationsListResponse.hasErrors()) {
            getManipulationsListResponse.getErrors().forEach(System.out::println);
        } else {
            System.out.println("Manipulations list:\n");
            getManipulationsListResponse.getManipulationList().forEach(System.out::println);
        }
    }
}
