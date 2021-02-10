package lesson_12.core.responses.reader;

import lesson_12.core.responses.CoreError;
import lesson_12.core.responses.CoreResponse;

import java.util.List;

public class FindReaderByFirstNameResponse extends CoreResponse {

    private boolean readerFind;

    public FindReaderByFirstNameResponse(boolean readerFind) {
        this.readerFind = readerFind;
    }

    public FindReaderByFirstNameResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isReaderFind() {
        return readerFind;
    }

}
