package book_library.core.services.ReaderBooks;

import book_library.core.database.Book.BookRepository;
import book_library.core.database.Reader.ReaderRepository;
import book_library.core.database.ReaderBook.ReaderBookRepository;
import book_library.core.domain.ReaderBook;
import book_library.core.requests.ReaderBook.TakeBookRequest;
import book_library.core.responses.CoreError;
import book_library.core.responses.ReaderBook.TakeBookResponse;
import book_library.core.validators.ReaderBook.TakeBookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class TakeBookService {

    @Autowired
    private ReaderBookRepository readerBookRepository;

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TakeBookValidator validator;

    public TakeBookResponse execute(TakeBookRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new TakeBookResponse(errors);
        }
        ReaderBook readerBook= new ReaderBook(readerRepository.getReaderById(request.getReaderId()), bookRepository.getBookById(request.getBookId()), request.getBookOutDate());
        readerBookRepository.save(readerBook);
        return new TakeBookResponse(readerBook);
    }
}
