package lesson_12.core.services.book;

import lesson_12.core.database.book.ElectronicLibraryRepository;
import lesson_12.core.domain.Book;
import lesson_12.core.requests.book.GetAllBooksRequest;
import lesson_12.core.responses.book.GetAllBooksResponse;
import lesson_12.core.services.book.validators.GetAllBooksValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class GetAllBooksService {

    @Autowired
    private ElectronicLibraryRepository electronicLibrary;

    @Autowired
    private GetAllBooksValidator getAllBooksValidator;

    public GetAllBooksResponse execute(GetAllBooksRequest request) {
        List<Book> books = electronicLibrary.getElectronicLibrary();
        return new GetAllBooksResponse(books);
    }
}
