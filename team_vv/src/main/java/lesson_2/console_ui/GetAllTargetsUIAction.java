package lesson_2.console_ui;

import lesson_2.Target;
import lesson_2.services.GetAllTargetsService;

public class GetAllTargetsUIAction implements UIAction {

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
