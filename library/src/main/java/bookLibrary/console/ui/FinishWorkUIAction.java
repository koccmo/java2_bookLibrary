package bookLibrary.console.ui;

import bookLibrary.core.request.ExitRequest;
import bookLibrary.core.service.FinishWorkService;
import bookLibrary.dependency_injection.DIComponent;
import bookLibrary.dependency_injection.DIDependency;

@DIComponent
public class FinishWorkUIAction implements UIAction{
    @DIDependency private FinishWorkService finishWorkService;


    @Override
    public void execute() {
        ExitRequest exitRequest= new ExitRequest();
        finishWorkService.execute(exitRequest);
        System.out.println("Good Bye!");
    }
}
