package book_library.core.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reader_books")
public class ReaderBook {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="reader_id")
    private Long readerId;

    @Column(name="book_id")
    private Long bookId;

    @Column(name="book_out_data")
    private Date bookOutData;

    @Column(name="book_return_data")
    private Date bookReturnData;

    public ReaderBook() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReaderId() {
        return readerId;
    }

    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Date getBookOutData() {
        return bookOutData;
    }

    public void setBookOutData(Date bookOutData) {
        this.bookOutData = bookOutData;
    }

    public Date getBookReturnData() {
        return bookReturnData;
    }

    public void setBookReturnData(Date bookReturnData) {
        this.bookReturnData = bookReturnData;
    }
}
