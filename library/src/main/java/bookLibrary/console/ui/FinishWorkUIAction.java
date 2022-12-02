package bookLibrary.console.ui;

import bookLibrary.core.request.ExitRequest;
import bookLibrary.core.service.FinishWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FinishWorkUIAction implements UIAction{
    @Autowired private FinishWorkService finishWorkService;


    @Override
    public void execute() {
        ExitRequest exitRequest= new ExitRequest();
        finishWorkService.execute(exitRequest);
        System.out.println("Good Bye!");
    }
}
