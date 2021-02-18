package java2.application_target_list.web_ui.controllers.rest;

import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.requests.user.DeleteUserRequest;
import java2.application_target_list.core.requests.user.GetUserRequest;
import java2.application_target_list.core.requests.user.UpdateUserRequest;
import java2.application_target_list.core.responses.user.AddUserResponse;
import java2.application_target_list.core.responses.user.DeleteUserResponse;
import java2.application_target_list.core.responses.user.GetUserResponse;
import java2.application_target_list.core.responses.user.UpdateUserResponse;
import java2.application_target_list.core.services.user.AddUserService;
import java2.application_target_list.core.services.user.DeleteUserService;
import java2.application_target_list.core.services.user.GetUserService;
import java2.application_target_list.core.services.user.UpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired private GetUserService getUserService;
    @Autowired private AddUserService addUserService;
    @Autowired private DeleteUserService deleteUserService;
    @Autowired private UpdateUserService updateUserService;

    @GetMapping(path = "{id}", produces = "application/json")
    public GetUserResponse getUser(@PathVariable Long id) {
        GetUserRequest getUserRequest = new GetUserRequest(id);
        return getUserService.execute(getUserRequest);
    }

    @PostMapping(path = "/",
             consumes = "application/json",
             produces = "application/json")
    public AddUserResponse addUser(@RequestBody AddUserRequest addUserRequest){
        return addUserService.execute(addUserRequest);
    }

    @PutMapping(path = "/{userIdToChange}", consumes = "application/json", produces = "application/json")
    public UpdateUserResponse updateUser(@RequestBody UpdateUserRequest updateUserRequest){
        return updateUserService.execute(updateUserRequest);
    }

    @DeleteMapping(path = "/{userIdToDelete}", produces = "application/json")
    public DeleteUserResponse deleteUser(@PathVariable Long userIdToDelete) {
        DeleteUserRequest deleteUserRequest = new DeleteUserRequest(userIdToDelete);
        return deleteUserService.execute(deleteUserRequest);
    }
}
