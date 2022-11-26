package bookLibrary.core.service;

import bookLibrary.core.dataBase.DataBase;
import bookLibrary.core.request.DeleteBookRequest;
import bookLibrary.core.response.CoreError;
import bookLibrary.core.response.DeleteBookResponse;
import bookLibrary.core.service.validators.DeleteBookValidation;
import bookLibrary.dependency_injection.DIComponent;
import bookLibrary.dependency_injection.DIDependency;

import java.util.List;
@DIComponent
public class DeleteBookService {
    @DIDependency private DataBase dataBase;
    @DIDependency private DeleteBookValidation deleteBookValidation;


    public DeleteBookResponse execute(DeleteBookRequest deleteBookRequest) {
        List<CoreError> errors = deleteBookValidation.validate(deleteBookRequest, dataBase);
        if(errors.isEmpty()) {
            Long bookId = Long.parseLong(deleteBookRequest.getId());
            dataBase.deleteBook(bookId);
            DeleteBookResponse deleteBookResponse = new DeleteBookResponse(true);
            return deleteBookResponse;
        } else {
            DeleteBookResponse deleteBookResponse = new DeleteBookResponse(errors);
            return deleteBookResponse;
        }
    }
}
