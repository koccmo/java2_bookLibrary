package bookLibrary.core.request;

import java.util.Date;

public class TakeBookRequest {
    private String readerId;
    private String bookId;
    private Date whenBookTaken;

    public TakeBookRequest(String readerId, String bookId, Date whenBookTaken) {
        this.readerId = readerId;
        this.bookId = bookId;
        this.whenBookTaken = whenBookTaken;
    }

    public String getReaderId() {
        return readerId;
    }

    public String getBookId() {
        return bookId;
    }

    public Date getWhenBookTaken() {
        return whenBookTaken;
    }
}
