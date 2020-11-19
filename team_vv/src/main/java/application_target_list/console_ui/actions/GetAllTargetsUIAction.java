package application_target_list.console_ui.actions;

import application_target_list.Target;
import application_target_list.console_ui.UIAction;
import application_target_list.core.requests.GetAllTargetsRequest;
import application_target_list.core.responses.GetAllTargetsResponse;
import application_target_list.core.services.GetAllTargetsService;

public class GetAllTargetsUIAction implements UIAction {

    private GetAllTargetsService getAllTargetsService;

    public GetAllTargetsUIAction(GetAllTargetsService getAllTargetsService) {
        this.getAllTargetsService = getAllTargetsService;
    }

    @Override
    public void execute() {
        GetAllTargetsRequest request = new GetAllTargetsRequest();
        GetAllTargetsResponse response = getAllTargetsService.execute(request);
        if (response.getTargetList().size() == 0) {
            System.out.println("----------");
            System.out.println("Targets list is empty");
            System.out.println("----------");
        } else {
           for (Target target : response.getTargetList()){
               System.out.println(target.getId() + ". " +
                       target.getName() + " [" + target.getDescription() + "] " +
                       target.getDeadline() + " days");
           }
            System.out.println("----------");
        }

    }

}
