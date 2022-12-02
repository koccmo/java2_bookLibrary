package bookLibrary.core.service;

import bookLibrary.core.domain.Book;
import bookLibrary.core.dataBase.DataBase;
import bookLibrary.core.request.AddBookRequest;
import bookLibrary.core.response.AddBookResponse;
import bookLibrary.core.response.CoreError;
import bookLibrary.core.service.validators.AddBookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class AddBookService {
    @Autowired
    private DataBase dataBase;
    @Autowired private AddBookValidator addBookValidator;


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
