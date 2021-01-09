package lesson_5.core.requests;

public class DeleteBookByAuthorRequest {

    private String bookAuthor;

    public DeleteBookByAuthorRequest(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }
}
