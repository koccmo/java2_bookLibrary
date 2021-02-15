package book_library.core.requests.ReaderBook;

import java.util.Date;

public class TakeBookRequest {
    private Long readerId;
    private Long BookId;
    private Date bookOutDate;

    public TakeBookRequest(Long readerId, Long bookId, Date bookOutDate) {
        this.readerId = readerId;
        BookId = bookId;
        this.bookOutDate = bookOutDate;
    }

    public Long getReaderId() {
        return readerId;
    }

    public Long getBookId() {
        return BookId;
    }

    public Date getBookOutDate() {
        return bookOutDate;
    }
}
