package java2.application_target_list.console_ui.actions;

import java2.application_target_list.core.requests.AddTargetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java2.application_target_list.console_ui.UIAction;
import java2.application_target_list.core.responses.AddTargetResponse;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.services.AddTargetService;



import java.util.Scanner;

@Component
public class AddTargetUIAction implements UIAction {

    @Autowired private AddTargetService addTargetService;

   private final Scanner scr = new Scanner(System.in);

    @Override
    public void execute() {

        while (true){
            String targetName = getNameFromUser();
            String targetDescription = getDescriptionFromUser();
            Integer targetDeadline = getDeadlineFromUser();

            AddTargetRequest request = createRequest(targetName,targetDescription,targetDeadline);
            AddTargetResponse response = createResponse(request);

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
        System.out.println("Your target was added to list.");
        System.out.println("----------");
    }

    private void printResponseErrors(AddTargetResponse response){
        response.getErrorList().forEach(System.out::println);
    }

    private AddTargetResponse createResponse(AddTargetRequest request){
        return addTargetService.execute(request);
    }

    private AddTargetRequest createRequest(String targetName, String targetDescription, Integer targetDeadline){
        return new AddTargetRequest(targetName,targetDescription,targetDeadline);
    }

    private String getNameFromUser(){
        System.out.print("Enter target name: ");
        return scr.nextLine();
    }

    private String getDescriptionFromUser(){
        System.out.print("Enter target description: ");
        return scr.nextLine();
    }

    private Integer getDeadlineFromUser(){
        System.out.print("Enter target deadline(days): ");
        return Integer.parseInt(scr.nextLine());
    }
}
