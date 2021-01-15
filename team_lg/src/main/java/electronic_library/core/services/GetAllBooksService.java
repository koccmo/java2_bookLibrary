package electronic_library.core.services;

import electronic_library.core.database.ElectronicLibrary;
import electronic_library.core.domain.Book;
import electronic_library.core.requests.GetAllBooksRequest;
import electronic_library.core.responses.GetAllBooksResponse;
import electronic_library.core.services.validators.GetAllBooksValidator;
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
