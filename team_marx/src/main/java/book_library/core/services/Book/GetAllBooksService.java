package book_library.core.services.Book;

import book_library.core.domain.Book;
import book_library.core.database.Book.BookRepository;
import book_library.core.requests.Book.GetAllBooksRequest;
import book_library.core.responses.Book.GetAllBooksResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Component
@Transactional
public class GetAllBooksService {

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public GetAllBooksResponse execute(GetAllBooksRequest request) {
        List<Book> books = bookRepository.getAllBooks();
        return new GetAllBooksResponse(books);
    }
}
