package lesson_3.core.services;

import lesson_3.core.database.ElectronicLibrary;
import lesson_3.core.domain.Book;
import lesson_3.core.requests.GetAllBooksRequest;
import lesson_3.core.responses.GetAllBooksResponse;

import java.util.List;

public class GetAllBooksService {
    private ElectronicLibrary electronicLibrary;

    public GetAllBooksService(ElectronicLibrary electronicLibrary) {
        this.electronicLibrary = electronicLibrary;
    }

    public GetAllBooksResponse execute(GetAllBooksRequest request) {
        List<Book> books = electronicLibrary.getElectronicLibrary();
        return new GetAllBooksResponse(books);
    }
}
