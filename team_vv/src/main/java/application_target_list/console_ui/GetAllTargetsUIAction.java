package application_target_list.console_ui;

import application_target_list.Target;
import application_target_list.services.GetAllTargetsService;

public class GetAllTargetsUIAction implements UIAction{

    private GetAllTargetsService getAllTargetsService;

    public GetAllTargetsUIAction(GetAllTargetsService getAllTargetsService) {
        this.getAllTargetsService = getAllTargetsService;
    }

    @Override
    public void execute() {
        if (isTargetsListEmpty()) {
            System.out.println("----------");
            System.out.println("Targets list is empty");
            System.out.println("----------");
        } else {
           for (Target target : getAllTargetsService.execute()){
               System.out.println(target.getId() + ". " +
                       target.getName() + " [" + target.getDescription() + "] " +
                       target.getDeadline() + " days");
           }
            System.out.println("----------");
        }

    }

    private boolean isTargetsListEmpty(){
        return getAllTargetsService.execute().size() == 0;
    }
}
