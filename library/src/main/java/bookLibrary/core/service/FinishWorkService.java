package bookLibrary.core.service;

import bookLibrary.core.dataBase.DataBase;
import bookLibrary.core.request.ExitRequest;
import bookLibrary.core.response.ExitResponse;
import bookLibrary.dependency_injection.DIComponent;
import bookLibrary.dependency_injection.DIDependency;

@DIComponent
public class FinishWorkService {
    @DIDependency private DataBase dataBase;


    public ExitResponse execute(ExitRequest exitRequest) {
        dataBase.finishWork();
        ExitResponse exitResponse = new ExitResponse();
        return exitResponse;
    }
}
