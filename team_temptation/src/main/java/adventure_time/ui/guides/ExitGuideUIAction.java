package adventure_time.ui.guides;

import ui.UIAction;

public class ExitGuideUIAction implements UIAction {

    @Override
    public void execute() {
        System.out.println("Good by!");
        System.exit(0);
    }
}
