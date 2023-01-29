package bookLibrary.core.response;

import java.util.List;

public class TakeBookResponse extends CoreResponse{
    private boolean bookToken;


    public TakeBookResponse(List<CoreError> errors) {
        super(errors);
    }

    public TakeBookResponse(boolean bookToken) {
        this.bookToken = bookToken;
    }

    public boolean isBookToken() {
        return bookToken;
    }


}
