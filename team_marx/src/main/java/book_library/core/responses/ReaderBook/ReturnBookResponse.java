package book_library.core.responses.ReaderBook;

import book_library.core.domain.ReaderBook;
import book_library.core.responses.CoreError;
import book_library.core.responses.CoreResponse;

import java.util.List;

public class ReturnBookResponse extends CoreResponse {

    private Long updatedReaderBookId;

    public ReturnBookResponse(List<CoreError> errors) {
        super(errors);
    }

    public ReturnBookResponse(Long updatedReaderBookId) {
        this.updatedReaderBookId = updatedReaderBookId;
    }

    public Long getUpdatedReaderBookId() {
        return updatedReaderBookId;
    }
}
