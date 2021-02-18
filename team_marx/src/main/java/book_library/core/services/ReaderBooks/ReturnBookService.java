package book_library.core.services.ReaderBooks;

import book_library.core.requests.ReaderBook.ReturnBookRequest;
import book_library.core.responses.CoreError;
import book_library.core.responses.ReaderBook.ReturnBookResponse;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class ReturnBookService {
    public ReturnBookResponse execute(ReturnBookRequest request) {
        return new ReturnBookResponse(new ArrayList<>());
    }
}
