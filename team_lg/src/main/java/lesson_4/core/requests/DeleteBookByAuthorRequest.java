package lesson_4.core.requests;

public class DeleteBookByAuthorRequest {

    private String bookAuthor;

    public DeleteBookByAuthorRequest(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }
}
