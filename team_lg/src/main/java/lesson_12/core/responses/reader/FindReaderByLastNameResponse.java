package lesson_12.core.responses.reader;

import lesson_12.core.responses.CoreError;
import lesson_12.core.responses.CoreResponse;

import java.util.List;

public class FindReaderByLastNameResponse extends CoreResponse {

    private boolean readerFind;

    public FindReaderByLastNameResponse(boolean readerFind) {
        this.readerFind = readerFind;
    }

    public FindReaderByLastNameResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isReaderFind() {
        return readerFind;
    }
}
