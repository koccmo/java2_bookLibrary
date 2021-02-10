package lesson_12.core.responses.reader;

import lesson_12.core.responses.CoreError;
import lesson_12.core.responses.CoreResponse;

import java.util.List;

public class DeleteReaderByFirstNameResponse extends CoreResponse {

    private boolean readerRemoved;

    public DeleteReaderByFirstNameResponse(boolean readerRemoved) {
        this.readerRemoved = readerRemoved;
    }

    public DeleteReaderByFirstNameResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isReaderRemoved() {
        return readerRemoved;
    }

}
