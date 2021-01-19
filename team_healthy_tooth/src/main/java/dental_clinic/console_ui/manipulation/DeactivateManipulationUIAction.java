package dental_clinic.console_ui.manipulation;

import dental_clinic.console_ui.InputFormatsValidator;
import dental_clinic.console_ui.UIAction;
import dental_clinic.core.requests.manipulation.DeactivateManipulationRequest;
import dental_clinic.core.requests.manipulation.GetManipulationsListRequest;
import dental_clinic.core.responses.manipulation.DeactivateManipulationResponse;
import dental_clinic.core.responses.manipulation.GetManipulationsListResponse;
import dental_clinic.core.services.manipulation.DeactivateManipulationService;
import dental_clinic.core.services.manipulation.GetManipulationsListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeactivateManipulationUIAction implements UIAction {

    @Autowired
    private DeactivateManipulationService deactivateManipulationService;
    @Autowired
    private InputFormatsValidator inputFormatsValidator;
    @Autowired
    private GetManipulationsListService getManipulationsListService;

    @Override
    public void execute() {

        GetManipulationsListRequest getManipulationsListRequest = new GetManipulationsListRequest();
        GetManipulationsListResponse getManipulationsListResponse = getManipulationsListService.execute(getManipulationsListRequest);
        if (getManipulationsListResponse.hasErrors()) {
            System.out.println("Manipulation's database is empty");
        } else {
            getManipulationsListResponse.getManipulationList().forEach(System.out::println);
            Long id = inputFormatsValidator.inputLong("\nPlease input manipulation id");

            DeactivateManipulationRequest deactivateManipulationRequest = new DeactivateManipulationRequest(id);
            DeactivateManipulationResponse deactivateManipulationResponse = deactivateManipulationService.execute(deactivateManipulationRequest);

            if (deactivateManipulationResponse.hasErrors()) {
                deactivateManipulationResponse.getErrors().forEach(System.out::println);
            } else {
                System.out.println("Manipulation with id " +
                        deactivateManipulationRequest.getId() + " is deactivated");
            }
        }
    }
}
