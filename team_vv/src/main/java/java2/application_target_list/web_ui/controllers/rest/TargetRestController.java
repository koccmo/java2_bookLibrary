package java2.application_target_list.web_ui.controllers.rest;

import java2.application_target_list.core.requests.target.*;
import java2.application_target_list.core.responses.target.*;
import java2.application_target_list.core.services.target.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostRemove;

@RestController
@RequestMapping("/target")
public class TargetRestController {

    @Autowired private GetTargetService getTargetService;
    @Autowired private AddTargetService addTargetService;
    @Autowired private UpdateTargetService updateTargetService;
    @Autowired private DeleteTargetService deleteTargetService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public GetTargetResponse getTarget(@PathVariable Long id) {
        GetTargetRequest getTargetRequest = new GetTargetRequest(id);
        return getTargetService.execute(getTargetRequest);
    }

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public AddTargetResponse addTarget(@RequestBody AddTargetRequest addTargetRequest) {
        return addTargetService.execute(addTargetRequest);
    }

    @PutMapping(path = "/{targetIdToChange}",
            consumes = "application/json",
            produces = "application/json")
    public UpdateTargetResponse updateTarget(@RequestBody UpdateTargetRequest updateTargetRequest) {
        return updateTargetService.execute(updateTargetRequest);
    }

    @DeleteMapping(path = "/{targetIdToDelete}",
            produces = "application/json")
    public DeleteTargetResponse deleteTarget(@PathVariable Long targetIdToDelete) {
        DeleteTargetRequest deleteTargetRequest = new DeleteTargetRequest(targetIdToDelete);
        return deleteTargetService.execute(deleteTargetRequest);
    }
}
