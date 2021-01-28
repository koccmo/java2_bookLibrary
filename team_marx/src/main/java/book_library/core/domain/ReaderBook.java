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

    @OneToMany
    @JoinColumn(name = "reader_id", nullable = false)
    private Reader reader;

    @OneToMany
    @JoinColumn(name="book_id", nullable = false)
    private Book book;

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
