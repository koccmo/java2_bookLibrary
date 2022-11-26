package bookLibrary.core.service;

import bookLibrary.core.dataBase.DataBase;
import bookLibrary.core.request.FindByAuthorRequest;
import bookLibrary.core.response.FindByAuthorResponse;
import bookLibrary.dependency_injection.DIComponent;
import bookLibrary.dependency_injection.DIDependency;

@DIComponent
public class FindBookByAuthorService {
    @DIDependency private DataBase dataBase;


    public FindByAuthorResponse execute(FindByAuthorRequest findByAuthorRequest) {
        FindByAuthorResponse findByAuthorResponse =
                new FindByAuthorResponse(dataBase.findByAuthor(findByAuthorRequest.getAuthor()));
        return findByAuthorResponse;
    }
}
