package team_VK.application.core.requests;

public class RemoveBookRequest {

    long bookId;
    String bookTitle;

    public RemoveBookRequest(long bookId, String bookTitle) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
    }

    public long getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }
}
