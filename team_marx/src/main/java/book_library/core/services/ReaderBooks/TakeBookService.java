package book_library.core.services.ReaderBooks;

import book_library.core.domain.ReaderBook;
import book_library.core.requests.ReaderBook.TakeBookRequest;
import book_library.core.responses.ReaderBook.TakeBookResponse;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class TakeBookService {
    public TakeBookResponse execute(TakeBookRequest request) {
        return new TakeBookResponse(new ReaderBook());
    }
}
