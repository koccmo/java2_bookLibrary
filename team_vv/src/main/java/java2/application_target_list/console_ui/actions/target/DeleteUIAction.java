package java2.application_target_list.console_ui.actions.target;

import java2.application_target_list.core.requests.target.DeleteTargetRequest;
import java2.application_target_list.config.TargetListConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;


import java2.application_target_list.console_ui.UIAction;
import java2.application_target_list.core.responses.target.DeleteTargetResponse;
import java2.application_target_list.core.services.target.DeleteTargetService;


import java.util.Scanner;

@Component
public class DeleteUIAction implements UIAction {

    @Autowired DeleteTargetService deleteTargetService;
    private final Scanner scr = new Scanner(System.in);


    @Override
    public void execute() {
        while (true) {
            Long targetId = getIdFromUser();

            DeleteTargetRequest request = createRequest(targetId);
            DeleteTargetResponse response = createResponse(request);

            if (response.hasErrors()) {
                printResponseErrors(response);
            } else {
                printResponseResultMessage();
                break;
            }
        }
    }

    private void printResponseResultMessage(){
        System.out.println("----------");
        System.out.println("Target was deleted!");
        System.out.println("----------");
    }

    private void printResponseErrors(DeleteTargetResponse response){
        response.getErrorList().forEach(System.out::println);
    }

    private DeleteTargetResponse createResponse(DeleteTargetRequest request){
        return deleteTargetService.execute(request);
    }

    private DeleteTargetRequest createRequest(Long targetId){
        return new DeleteTargetRequest(targetId);
    }

    private Long getIdFromUser(){
        System.out.print("Enter target ID: ");
        return Long.parseLong(scr.nextLine());
    }

}
