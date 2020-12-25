package java2.application_target_list.console_ui.actions;

import org.springframework.stereotype.Component;


import java2.application_target_list.console_ui.UIAction;

@Component
public class MenuUIAction implements UIAction {

    @Override
    public void execute() {
        System.out.println("Actions: ");
        System.out.println("[1] Show targets list");
        System.out.println("[2] Add target");
        System.out.println("[3] Delete target");
        System.out.println("[4] Change target name");
        System.out.println("[5] Change target description");
        System.out.println("[6] Change target deadline");
        System.out.println("[7] Search target by name");
        System.out.println("[8] Search target by description");
        System.out.println("[0] Quit");
    }
}
