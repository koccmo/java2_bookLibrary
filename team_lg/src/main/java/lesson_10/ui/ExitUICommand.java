package lesson_10.ui;

import org.springframework.stereotype.Component;

@Component
public class ExitUICommand implements UICommand {

    @Override
    public void execute() {
        System.out.println("Thank Your! Good by!");
        System.exit(0);
    }
}
