package java2.application_target_list.console_ui.actions.user;

import java2.application_target_list.console_ui.UIAction;
import java2.application_target_list.core.requests.user.DeleteUserRequest;
import java2.application_target_list.core.responses.user.DeleteUserResponse;
import java2.application_target_list.core.services.user.DeleteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteUserUIAction implements UIAction {

    @Autowired DeleteUserService deleteUserService;
    private final Scanner scr = new Scanner(System.in);

    @Override
    public void execute() {
        while (true) {
            Long userId = getIdFromUser();

            DeleteUserRequest deleteUserRequest = createRequest(userId);
            DeleteUserResponse deleteUserResponse = createResponse(deleteUserRequest);

            if (deleteUserResponse.hasErrors()) {
                printResponseErrors(deleteUserResponse);
            } else {
                printResponseResultMessage();
                break;
            }
        }
    }

    private void printResponseErrors(DeleteUserResponse deleteUserResponse){
        deleteUserResponse.getErrorList().forEach(System.out::println);
    }

    private DeleteUserResponse createResponse(DeleteUserRequest deleteUserRequest){
        return deleteUserService.execute(deleteUserRequest);
    }

    private DeleteUserRequest createRequest(Long targetId){
        return new DeleteUserRequest(targetId);
    }

    private Long getIdFromUser(){
        System.out.print("Enter target ID: ");
        return Long.parseLong(scr.nextLine());
    }

    private void printResponseResultMessage(){
        System.out.println("----------");
        System.out.println("User was deleted!");
        System.out.println("----------");
    }
}
