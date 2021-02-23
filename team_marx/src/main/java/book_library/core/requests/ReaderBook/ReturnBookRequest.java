package book_library.core.requests.ReaderBook;

import java.util.Date;

public class ReturnBookRequest {
    private Long readerId;
    private Long BookId;
    private Date bookReturnDate;

    public ReturnBookRequest(Long readerId, Long bookId, Date bookReturnDate) {
        this.readerId = readerId;
        BookId = bookId;
        this.bookReturnDate = bookReturnDate;
    }

    public Long getReaderId() {
        return readerId;
    }

    public Long getBookId() {
        return BookId;
    }

    public Date getBookReturnDate() {
        return bookReturnDate;
    }
}
