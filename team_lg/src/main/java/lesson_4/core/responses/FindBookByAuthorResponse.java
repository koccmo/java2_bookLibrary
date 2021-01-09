package lesson_4.core.responses;

import java.util.List;

public class FindBookByAuthorResponse  extends CoreResponse {

    private boolean bookFind;

    public FindBookByAuthorResponse(boolean bookFind) {
        this.bookFind = bookFind;
    }

    public FindBookByAuthorResponse(List<CoreError> errors, boolean bookFind) {
        super(errors);
        this.bookFind = bookFind;
    }

    public boolean isBookFind() {
        return bookFind;
    }
}
