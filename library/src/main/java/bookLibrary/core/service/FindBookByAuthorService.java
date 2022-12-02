package bookLibrary.core.service;

import bookLibrary.core.dataBase.DataBase;
import bookLibrary.core.request.FindByAuthorRequest;
import bookLibrary.core.response.FindByAuthorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindBookByAuthorService {
    @Autowired
    private DataBase dataBase;


    public FindByAuthorResponse execute(FindByAuthorRequest findByAuthorRequest) {
        FindByAuthorResponse findByAuthorResponse =
                new FindByAuthorResponse(dataBase.findByAuthor(findByAuthorRequest.getAuthor()));
        return findByAuthorResponse;
    }
}
