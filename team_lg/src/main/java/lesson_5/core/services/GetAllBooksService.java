package lesson_5.core.services;

import lesson_5.core.database.ElectronicLibrary;
import lesson_5.core.domain.Book;
import lesson_5.core.requests.GetAllBooksRequest;
import lesson_5.core.responses.GetAllBooksResponse;
import lesson_5.core.services.validators.GetAllBooksValidator;

import java.util.List;

public class GetAllBooksService {
    private ElectronicLibrary electronicLibrary;
    private GetAllBooksValidator getAllBooksValidator;

    public GetAllBooksService(ElectronicLibrary electronicLibrary, GetAllBooksValidator getAllBooksValidator) {
        this.electronicLibrary = electronicLibrary;
        this.getAllBooksValidator = getAllBooksValidator;
    }

    public GetAllBooksResponse execute(GetAllBooksRequest request) {
        List<Book> books = electronicLibrary.getElectronicLibrary();
        return new GetAllBooksResponse(books);
    }
}
