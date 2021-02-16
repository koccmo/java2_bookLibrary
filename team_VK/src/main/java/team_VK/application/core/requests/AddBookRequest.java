package team_VK.application.core.requests;

public class AddBookRequest {

    public String bookTitle;
    public String bookAuthor;
    public int bookingDurationPermitted;

    public AddBookRequest(String bookTitle, String bookAuthor, int bookingDurationPermitted) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookingDurationPermitted = bookingDurationPermitted;
    }

    public AddBookRequest() {
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public int getBookingDurationPermitted() {
        return bookingDurationPermitted;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public void setBookingDurationPermitted(int bookingDurationPermitted) {
        this.bookingDurationPermitted = bookingDurationPermitted;
    }
}
