package application_target_list.console_ui.actions;

import application_target_list.console_ui.UIAction;
import application_target_list.core.database.Target;
import application_target_list.core.requests.SearchTargetByDescriptionRequest;
import application_target_list.core.responses.CoreError;
import application_target_list.core.responses.SearchTargetByDescriptionResponse;
import application_target_list.core.services.SearchTargetByDescriptionService;


import java.util.Scanner;

public class SearchTargetByDescriptionUIAction implements UIAction {

    private final SearchTargetByDescriptionService searchTargetByDescriptionService;
    private final Scanner scr = new Scanner(System.in);

    public SearchTargetByDescriptionUIAction(SearchTargetByDescriptionService searchTargetByDescriptionService) {
        this.searchTargetByDescriptionService = searchTargetByDescriptionService;
    }

    @Override
    public void execute() {
        while (true){
            String targetName = getDescriptionFromUser();

            SearchTargetByDescriptionRequest request = createRequest(targetName);
            SearchTargetByDescriptionResponse response = createResponse(request);

            if (response.hasErrors()) {
                printResponseErrors(response);
            } else {
                printResponseResultMessage(response);
                break;
            }
        }
    }

    private void printResponseResultMessage(SearchTargetByDescriptionResponse response){
        if (response.getTargetList().isEmpty()){
            System.out.println("----------");
            System.out.println("Target not found!");
            System.out.println("----------");
        } else {

            for (Target target : response.getTargetList()) {
                System.out.println(target.getId() + ". " +
                        target.getName() + " [" + target.getDescription() + "] " +
                        target.getDeadline() + " days");
            }
            System.out.println("----------");
        }
    }

    private void printResponseErrors(SearchTargetByDescriptionResponse response){
        for (CoreError error : response.getErrorList()) {
            System.out.println("Error: " + error.getField() + " " + error.getMessage());
        }
    }

    private SearchTargetByDescriptionResponse createResponse(SearchTargetByDescriptionRequest request){
        return searchTargetByDescriptionService.execute(request);
    }

    private SearchTargetByDescriptionRequest createRequest(String targetName){
        return new SearchTargetByDescriptionRequest(targetName);
    }

    private String getDescriptionFromUser(){
        System.out.print("Enter target description: ");
        return scr.nextLine();
    }

}
