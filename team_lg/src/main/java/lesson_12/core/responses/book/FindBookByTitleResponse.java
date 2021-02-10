package lesson_12.core.responses.book;

import lesson_12.core.responses.CoreError;
import lesson_12.core.responses.CoreResponse;

import java.util.List;

public class FindBookByTitleResponse extends CoreResponse {

    private boolean bookFind;

    public FindBookByTitleResponse(boolean bookFind) {
        this.bookFind = bookFind;
    }

    public FindBookByTitleResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isBookFind() {
        return bookFind;
    }
}
