package book_library.core.services;

import book_library.core.domain.Book;
import book_library.core.requests.GetAllBooksRequest;
import book_library.core.responses.GetAllBooksResponse;
import book_library.core.database.ElectronicLibrary;

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
