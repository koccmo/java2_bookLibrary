package adventure_time.ui.events;

import adventure_time.dependencies.DIComponent;
import adventure_time.ui.UIAction;
import org.springframework.stereotype.Component;

//@DIComponent
@Component
public class ExitEventUIAction implements UIAction {

    @Override
    public void execute() {
        System.out.println("This is the end, my only friend!");
        System.exit(0);
    }
}
