package lesson_5.core.requests;

public class FindBookByAuthorRequest {
    private String bookAuthor;

    public FindBookByAuthorRequest(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }
    public String getBookAuthor() {
        return bookAuthor;
    }
}
