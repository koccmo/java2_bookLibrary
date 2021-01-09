package lesson_5.core.responses;

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
