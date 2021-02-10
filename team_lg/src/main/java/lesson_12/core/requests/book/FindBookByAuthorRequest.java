package lesson_12.core.requests.book;

public class FindBookByAuthorRequest {

    private String bookAuthor;

    public FindBookByAuthorRequest(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

}
