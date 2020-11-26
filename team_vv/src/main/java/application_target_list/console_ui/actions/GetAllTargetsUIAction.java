package application_target_list.console_ui.actions;

import application_target_list.core.database.Target;
import application_target_list.console_ui.UIAction;
import application_target_list.core.requests.GetAllTargetsRequest;
import application_target_list.core.responses.GetAllTargetsResponse;
import application_target_list.core.services.GetAllTargetsService;

public class GetAllTargetsUIAction implements UIAction {

    private final GetAllTargetsService getAllTargetsService;

    public GetAllTargetsUIAction(GetAllTargetsService getAllTargetsService) {
        this.getAllTargetsService = getAllTargetsService;
    }

    @Override
    public void execute() {
        GetAllTargetsRequest request = createRequest();
        GetAllTargetsResponse response = createResponse(request);

        if (isTargetListEmpty(response)) {
            printResponseMessage();
        } else {
           printTargetList(response);
        }

    }

    private void printTargetList(GetAllTargetsResponse response){
        for (Target target : response.getTargetList()){
            System.out.println(target.getId() + ". " +
                    target.getName() + " [" + target.getDescription() + "] " +
                    target.getDeadline() + " days");
        }
        System.out.println("----------");
    }

    private void printResponseMessage(){
        System.out.println("----------");
        System.out.println("Targets list is empty");
        System.out.println("----------");
    }

    private boolean isTargetListEmpty(GetAllTargetsResponse response){
        return response.getTargetList().size() == 0;
    }

    private GetAllTargetsResponse createResponse(GetAllTargetsRequest request){
        return getAllTargetsService.execute(request);
    }

    private GetAllTargetsRequest createRequest(){
        return new GetAllTargetsRequest();
    }

}
