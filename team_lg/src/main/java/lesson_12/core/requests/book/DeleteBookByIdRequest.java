package lesson_12.core.requests.book;

public class DeleteBookByIdRequest {

    private Long bookIdToDelete;

    public DeleteBookByIdRequest(Long bookIdToDelete) {
        this.bookIdToDelete = bookIdToDelete;
    }

    public Long getBookIdToDelete() {
        return bookIdToDelete;
    }
}
