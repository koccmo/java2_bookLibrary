package team_VK.application.core.requests;

public class AddBookRequest {

    public String bookTitle;
    public String bookAuthor;

    public AddBookRequest(String bookTitle, String bookAuthor) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }
}
