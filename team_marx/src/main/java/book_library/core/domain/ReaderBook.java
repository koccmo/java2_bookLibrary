package book_library.core.domain;


import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "reader_books")
public class ReaderBook {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reader_id", nullable = false)
    private Reader reader;

    @ManyToOne
    @JoinColumn(name="book_id", nullable = false)
    private Book book;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="book_out_date")
    private Date bookOutData;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="book_return_date")
    private Date bookReturnData;

    public ReaderBook() {
    }

    public ReaderBook(Reader reader, Book book, Date bookOutData) {
        this.reader = reader;
        this.book = book;
        this.bookOutData = bookOutData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
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

    @Override
    public int hashCode() {
        return Objects.hash(reader, book, bookOutData, bookReturnData);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReaderBook readerBook = (ReaderBook) o;
        return reader.equals(readerBook.reader) &&
                book.equals(readerBook.book) &&
                bookOutData.equals(readerBook.bookOutData) &&
                bookReturnData.equals(readerBook.bookReturnData);
    }

    @Override
    public String toString() {
        return "ReaderBook{" +
                "id=" + id +
                ", reader='" + reader + '\'' +
                ", book='" + book + '\'' +
                ", bookOutData='" + bookOutData + '\'' +
                ", bookReturnData='" + bookReturnData + '\'' +
                '}';
    }
}
