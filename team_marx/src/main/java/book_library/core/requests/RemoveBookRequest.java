package book_library.core.requests;

public class RemoveBookRequest {

    private Long bookIdToRemove;

    public RemoveBookRequest(Long bookIdToRemove) {
        this.bookIdToRemove = bookIdToRemove;
    }

    public Long getBookIdToRemove() {
        return bookIdToRemove;
    }
}
