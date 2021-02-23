package book_library.core.responses.ReaderBook;

import book_library.core.domain.ReaderBook;
import book_library.core.responses.CoreError;
import book_library.core.responses.CoreResponse;

import java.util.List;

public class TakeBookResponse extends CoreResponse {

    private ReaderBook newReaderBook;

    public TakeBookResponse(List<CoreError> errors) {
        super(errors);
    }

    public TakeBookResponse(ReaderBook newReaderBook) {
        this.newReaderBook = newReaderBook;
    }

    public ReaderBook getNewReaderBook() {
        return newReaderBook;
    }
}
