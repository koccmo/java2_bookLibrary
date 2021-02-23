package dental_clinic.web_ui.controllers.rest;

import dental_clinic.core.requests.manipulation.AddManipulationRequest;
import dental_clinic.core.requests.manipulation.rest.GetManipulationRequest;
import dental_clinic.core.responses.manipulation.AddManipulationResponse;
import dental_clinic.core.responses.manipulation.rest.GetManipulationResponse;
import dental_clinic.core.services.manipulation.AddManipulationService;
import dental_clinic.core.services.manipulation.rest.GetManipulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manipulation")
public class ManipulationRestController {

    @Autowired private GetManipulationService getManipulationService;
    @Autowired private AddManipulationService addManipulationService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public GetManipulationResponse getManipulation(@PathVariable Long id) {
        GetManipulationRequest getManipulationRequest = new GetManipulationRequest(id);
        return getManipulationService.execute(getManipulationRequest);
    }

    @PostMapping(path = "/",
    consumes = "application/json",
    produces = "application/json")
    public AddManipulationResponse addManipulation(@RequestBody AddManipulationRequest addManipulationRequest) {
        return addManipulationService.execute(addManipulationRequest);
    }
}
