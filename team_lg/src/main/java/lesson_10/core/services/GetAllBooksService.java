package lesson_10.core.services;

import lesson_10.core.database.ElectronicLibrary;
import lesson_10.core.domain.Book;
import lesson_10.core.requests.GetAllBooksRequest;
import lesson_10.core.responses.GetAllBooksResponse;
import lesson_10.core.services.validators.GetAllBooksValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllBooksService {

    @Autowired
    private ElectronicLibrary electronicLibrary;

    @Autowired
    private GetAllBooksValidator getAllBooksValidator;

    public GetAllBooksResponse execute(GetAllBooksRequest request) {
        List<Book> books = electronicLibrary.getElectronicLibrary();
        return new GetAllBooksResponse(books);
    }
}
