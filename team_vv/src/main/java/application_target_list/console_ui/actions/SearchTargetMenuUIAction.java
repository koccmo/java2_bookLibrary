package application_target_list.console_ui.actions;

import application_target_list.console_ui.UIAction;

public class SearchTargetMenuUIAction implements UIAction {


    @Override
    public void execute() {
        System.out.println("Actions: ");
        System.out.println("[1] Search target by name");
        System.out.println("[2] Search target by description");
        System.out.println("[0] Back to Menu");
    }
}
