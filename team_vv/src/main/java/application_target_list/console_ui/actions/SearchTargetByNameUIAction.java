package application_target_list.console_ui.actions;

import application_target_list.core.requests.Paging;
import application_target_list.core.requests.SearchTargetByNameRequest;
import application_target_list.core.services.SearchTargetByNameService;
import application_target_list.console_ui.UIAction;
import application_target_list.core.database.Target;
import application_target_list.core.responses.CoreError;
import application_target_list.core.responses.SearchTargetByNameResponse;

import java.util.Scanner;

public class SearchTargetByNameUIAction implements UIAction {

    private final SearchTargetByNameService searchTargetByNameService;
    private final Scanner scr = new Scanner(System.in);

    public SearchTargetByNameUIAction(SearchTargetByNameService searchTargetByNameService) {
        this.searchTargetByNameService = searchTargetByNameService;
    }

    @Override
    public void execute() {
        while (true){
            String targetName = getNameFromUser();

            printPagingMessage();
            int numberFromUser = getNumberFromUser();

            SearchTargetByNameResponse response;
            if (isPagingNeeded(numberFromUser)){
                Integer pageNumber = getPageNumberFromUser();
                Integer pageSize = getPageSizeFromUser();
                SearchTargetByNameRequest request = createRequestWithPaging(targetName, pageNumber, pageSize);
                response = createResponse(request);

            } else {
                SearchTargetByNameRequest request = createRequest(targetName);
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

    private void printResponseResultMessage(SearchTargetByNameResponse response){
        if (response.getTargetsList().isEmpty()){
            System.out.println("----------");
            System.out.println("Target not found!");
            System.out.println("----------");
        } else {

            for (Target target : response.getTargetsList()) {
                System.out.println(target.getId() + ". " +
                        target.getName() + " [" + target.getDescription() + "] " +
                        target.getDeadline() + " days");
            }
            System.out.println("----------");
        }
    }

    private void printResponseErrors(SearchTargetByNameResponse response){
        for (CoreError error : response.getErrorList()) {
            System.out.println("Error: " + error.getField() + " " + error.getMessage());
        }
    }

    private SearchTargetByNameResponse createResponse(SearchTargetByNameRequest request){
        return searchTargetByNameService.execute(request);
    }

    private SearchTargetByNameRequest createRequest(String targetName){
        return new SearchTargetByNameRequest(targetName);
    }

    private SearchTargetByNameRequest createRequestWithPaging(String targetName, Integer pageNumber, Integer pageSize){
        Paging paging = new Paging(pageNumber, pageSize);
        return new SearchTargetByNameRequest(targetName, paging);
    }

    private String getNameFromUser(){
        System.out.print("Enter target name: ");
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
