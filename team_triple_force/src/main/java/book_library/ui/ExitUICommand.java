package book_library.ui;

import book_library.ui.UICommand;

public class ExitUICommand implements UICommand {

    @Override
    public void execute() {
        System.out.println("Thank Your! Good by!");
        System.exit(0);
    }
}
