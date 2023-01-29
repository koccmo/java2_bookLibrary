package bookLibrary.core.response;

import java.util.List;

public class GetBookIdResponse extends CoreResponse{
    private String bookId;

    public GetBookIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public GetBookIdResponse(String bookId) {
        this.bookId = bookId;
    }

    public String getBookId() {
        return bookId;
    }
}
