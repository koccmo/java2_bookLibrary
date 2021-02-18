package book_library.core.responses.ReaderBook;

import book_library.core.domain.ReaderBook;
import book_library.core.responses.CoreError;
import book_library.core.responses.CoreResponse;

import java.util.List;

public class ReturnBookResponse extends CoreResponse {

    private ReaderBook readerBook;

    public ReturnBookResponse(List<CoreError> errors) {
        super(errors);
    }

    public ReturnBookResponse(ReaderBook readerBook) {
        this.readerBook = readerBook;
    }

    public ReaderBook getReaderBook() {
        return readerBook;
    }
}
