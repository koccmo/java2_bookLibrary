package internet_store.console_ui.actions;

import internet_store.Target;
import internet_store.console_ui.UIAction;
import internet_store.core.requests.GetAllTargetsRequest;
import internet_store.core.responses.GetAllTargetsResponse;
import internet_store.core.services.GetAllTargetsService;

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
