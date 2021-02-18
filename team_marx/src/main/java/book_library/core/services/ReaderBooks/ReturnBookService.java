package book_library.core.services.ReaderBooks;

import book_library.core.database.Book.BookRepository;
import book_library.core.database.Reader.ReaderRepository;
import book_library.core.database.ReaderBook.ReaderBookRepository;
import book_library.core.domain.ReaderBook;
import book_library.core.requests.ReaderBook.ReturnBookRequest;
import book_library.core.responses.CoreError;
import book_library.core.responses.ReaderBook.ReturnBookResponse;
import book_library.core.responses.ReaderBook.TakeBookResponse;
import book_library.core.validators.ReaderBook.ReturnBookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class ReturnBookService {

    @Autowired
    private ReaderBookRepository readerBookRepository;

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private BookRepository bookRepository;


    @Autowired
    ReturnBookValidator validator;

    public ReturnBookResponse execute(ReturnBookRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new ReturnBookResponse(errors);
        }
        ReaderBook readerBook= new ReaderBook(readerRepository.getReaderById(request.getReaderId()), bookRepository.getBookById(request.getBookId()), request.getBookReturnDate());
        readerBookRepository.returnBook(readerBook);
        return new ReturnBookResponse(readerBook);
    }
}
