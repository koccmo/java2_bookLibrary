package team_VK.application.core.requests;

public class SearchBookRequest {

    public String bookTitle;
    public String bookAuthor;
    public String bookIdString;

    public SearchBookRequest(String bookTitle, String bookAuthor, String bookIdString) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookIdString = bookIdString;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookIdString() {
        return bookIdString;
    }
}
