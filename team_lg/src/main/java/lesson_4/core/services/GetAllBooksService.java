package lesson_4.core.services;

import lesson_4.core.database.ElectronicLibrary;
import lesson_4.core.domain.Book;
import lesson_4.core.requests.GetAllBooksRequest;
import lesson_4.core.responses.GetAllBooksResponse;
import lesson_4.core.services.validators.GetAllBooksValidator;

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
