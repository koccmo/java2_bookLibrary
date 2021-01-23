package adventure_time.ui;

import org.springframework.stereotype.Component;

@Component
public class ExitUIAction implements UIAction {

    @Override
    public void execute() {
        System.out.println("This is the End, my only Friend!");
        System.exit(0);
    }

}
