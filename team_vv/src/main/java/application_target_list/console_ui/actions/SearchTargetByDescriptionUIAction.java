package application_target_list.console_ui.actions;

import application_target_list.console_ui.UIAction;
import application_target_list.core.database.Target;
import application_target_list.core.requests.Paging;
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

            printPagingMessage();
            int numberFromUser = getNumberFromUser();

            SearchTargetByDescriptionResponse response;
            if (isPagingNeeded(numberFromUser)){
                Integer pageNumber = getPageNumberFromUser();
                Integer pageSize = getPageSizeFromUser();
                SearchTargetByDescriptionRequest request = createRequestWithPaging(targetName, pageNumber, pageSize);
                response = createResponse(request);

            } else {
                SearchTargetByDescriptionRequest request = createRequest(targetName);
                response = createResponse(request);
            }


            if (response.hasErrors()) {
                printResponseErrors(response);
            } else {
                printResponseResultMessage(response);
                break;
            }
        }
    }

    private boolean isPagingNeeded(int number){
        return number == 2;
    }

    private void printPagingMessage(){
        System.out.println("Show all target list?");
        System.out.println("[1] YES");
        System.out.println("[2] NO");
    }

    private Integer getNumberFromUser(){
        return Integer.parseInt(scr.nextLine());
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

    private SearchTargetByDescriptionRequest createRequestWithPaging(String targetName, Integer pageNumber, Integer pageSize){
        Paging paging = new Paging(pageNumber, pageSize);
        return new SearchTargetByDescriptionRequest(targetName, paging);
    }

    private String getDescriptionFromUser(){
        System.out.print("Enter target description: ");
        return scr.nextLine();
    }

    private Integer getPageNumberFromUser(){
        System.out.print("Enter page number: ");
        return Integer.parseInt(scr.nextLine());
    }

    private Integer getPageSizeFromUser(){
        System.out.print("Enter page size: ");
        return Integer.parseInt(scr.nextLine());
    }

}
