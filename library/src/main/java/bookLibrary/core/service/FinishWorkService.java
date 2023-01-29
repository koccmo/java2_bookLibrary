package bookLibrary.core.service;

import bookLibrary.core.dataBase.DataBase;
import bookLibrary.core.request.ExitRequest;
import bookLibrary.core.response.ExitResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class FinishWorkService {
    @Autowired
    private DataBase dataBase;


    public ExitResponse execute(ExitRequest exitRequest) {
        dataBase.finishWork();
        ExitResponse exitResponse = new ExitResponse();
        return exitResponse;
    }
}
