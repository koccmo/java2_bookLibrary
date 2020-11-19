package lesson_3.console_ui.actions;

import lesson_3.Target;
import lesson_3.console_ui.UIAction;
import lesson_3.core.requests.GetAllTargetsRequest;
import lesson_3.core.responses.GetAllTargetsResponse;
import lesson_3.core.services.GetAllTargetsService;

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
