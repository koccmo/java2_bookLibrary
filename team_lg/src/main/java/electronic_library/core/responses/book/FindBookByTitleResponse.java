package electronic_library.core.responses.book;

import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.CoreResponse;

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
