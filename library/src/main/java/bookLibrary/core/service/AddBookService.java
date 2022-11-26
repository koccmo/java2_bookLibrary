package bookLibrary.core.service;

import bookLibrary.Book;
import bookLibrary.core.dataBase.DataBase;
import bookLibrary.core.request.AddBookRequest;
import bookLibrary.core.response.AddBookResponse;
import bookLibrary.core.response.CoreError;
import bookLibrary.core.service.validators.AddBookValidator;
import bookLibrary.dependency_injection.DIComponent;
import bookLibrary.dependency_injection.DIDependency;

import java.util.List;
@DIComponent
public class AddBookService {
    @DIDependency private DataBase dataBase;
    @DIDependency private AddBookValidator addBookValidator;


    public AddBookResponse execute (AddBookRequest addBookRequest) {
        List<CoreError> errors = addBookValidator.validate(addBookRequest, dataBase);
        if (errors.isEmpty()) {
            Book book = new Book(addBookRequest.getAuthor(), addBookRequest.getTitle());
            dataBase.addBook(book);
            AddBookResponse addBookResponse = new AddBookResponse(book);
            return addBookResponse;
        } else {
            AddBookResponse addBookResponse = new AddBookResponse(errors);
            return  addBookResponse;
        }
    }
}
