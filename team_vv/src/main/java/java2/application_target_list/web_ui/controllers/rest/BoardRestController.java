package java2.application_target_list.web_ui.controllers.rest;

import java2.application_target_list.core.requests.board.AddRecordRequest;
import java2.application_target_list.core.requests.board.DeleteRecordRequest;
import java2.application_target_list.core.requests.board.GetRecordRequest;
import java2.application_target_list.core.requests.board.SetRecordCompleteDateRequest;
import java2.application_target_list.core.responses.board.AddRecordResponse;
import java2.application_target_list.core.responses.board.DeleteRecordResponse;
import java2.application_target_list.core.responses.board.GetRecordResponse;
import java2.application_target_list.core.responses.board.SetRecordCompleteDateResponse;
import java2.application_target_list.core.services.board.AddRecordService;
import java2.application_target_list.core.services.board.DeleteRecordService;
import java2.application_target_list.core.services.board.GetRecordService;
import java2.application_target_list.core.services.board.SetRecordCompleteDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
public class BoardRestController {

    @Autowired private GetRecordService getRecordService;
    @Autowired private AddRecordService addRecordService;
    @Autowired private DeleteRecordService deleteRecordService;
    @Autowired private SetRecordCompleteDateService setRecordCompleteDateService;

    @GetMapping(path = "{id}", produces = "application/json")
    public GetRecordResponse getRecord(@PathVariable Long id){
        GetRecordRequest getRecordRequest = new GetRecordRequest(id);
        return getRecordService.execute(getRecordRequest);
    }

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public AddRecordResponse addRecord(@RequestBody AddRecordRequest addRecordRequest){
        return addRecordService.execute(addRecordRequest);
    }

    @DeleteMapping(path = "/{recordIdToDelete}", produces = "application/json")
    public DeleteRecordResponse deleteRecord(@PathVariable Long recordIdToDelete){
        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(recordIdToDelete);
        return deleteRecordService.execute(deleteRecordRequest);
    }

    @PutMapping(path = "/{recordIdToSetCompleteDate}",
            produces = "application/json")
    public SetRecordCompleteDateResponse setRecordCompleteDate(@PathVariable Long recordIdToSetCompleteDate){
        return setRecordCompleteDateService.execute(new SetRecordCompleteDateRequest(recordIdToSetCompleteDate));
    }

}
