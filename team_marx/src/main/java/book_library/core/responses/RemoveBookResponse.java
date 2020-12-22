package book_library.core.responses;

public class RemoveBookResponse extends CoreResponse {

    private boolean bookRemoved;

    public RemoveBookResponse(boolean bookRemoved) {
        this.bookRemoved = bookRemoved;
    }

    public boolean isBookRemoved() {
        return bookRemoved;
    }
}
