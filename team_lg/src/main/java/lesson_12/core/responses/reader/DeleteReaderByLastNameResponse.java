package lesson_12.core.responses.reader;

import lesson_12.core.responses.CoreError;
import lesson_12.core.responses.CoreResponse;

import java.util.List;

public class DeleteReaderByLastNameResponse extends CoreResponse {

    private boolean readerRemoved;

    public DeleteReaderByLastNameResponse(boolean readerRemoved) {
        this.readerRemoved = readerRemoved;
    }

    public DeleteReaderByLastNameResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isReaderRemoved() {
        return readerRemoved;
    }

}
