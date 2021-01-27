package java2.application_target_list.console_ui.actions.user;

import java2.application_target_list.console_ui.UIAction;
import java2.application_target_list.core.database.user.UserRepository;
import java2.application_target_list.core.requests.user.DeleteUserRequest;
import java2.application_target_list.core.responses.user.DeleteUserResponse;
import java2.application_target_list.core.services.user.DeleteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteUserUIAction implements UIAction {

    @Autowired DeleteUserService deleteUserService;
    @Autowired
    UserRepository userRepository;
    private final Scanner scr = new Scanner(System.in);

    @Override
    public void execute() {
        while (true) {

            if (userListIsEmpty()) {
                printBreakMessage();
                break;
            }

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
        System.out.print("Enter user ID: ");
        return Long.parseLong(scr.nextLine());
    }

    private void printResponseResultMessage(){
        System.out.println("----------");
        System.out.println("User was deleted!");
        System.out.println("----------");
    }

    private boolean userListIsEmpty(){
        return userRepository.getUsersList().isEmpty();
    }

    private void printBreakMessage(){
        System.out.println("----------");
        System.out.println("Users list is empty!");
        System.out.println("----------");
    }
}
