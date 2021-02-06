package electronic_library.core.services.book;

import electronic_library.core.database.book.ElectronicLibraryRepository;
import electronic_library.core.domain.Book;
import electronic_library.core.requests.book.GetAllBooksRequest;
import electronic_library.core.responses.book.GetAllBooksResponse;
import electronic_library.core.services.book.validators.GetAllBooksValidator;
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
