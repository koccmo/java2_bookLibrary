package bookLibrary.core.domain;


import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "reader_books")
public class ReaderBook {
    @Id
    @Column(name = "id")
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reader_id", nullable = false)
    private Reader reader;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "book_out_date", nullable = false)
    private Date bookOutDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "book_return_date")
    private Date bookReturnDate;

    public ReaderBook() {
    }

    public ReaderBook(Reader reader, Book book, Date bookOutDate) {
        this.reader = reader;
        this.book = book;
        this.bookOutDate = bookOutDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setBookOutDate(Date bookOutDate) {
        this.bookOutDate = bookOutDate;
    }

    public void setBookReturnDate(Date bookReturnDate) {
        this.bookReturnDate = bookReturnDate;
    }

    public Long getId() {
        return id;
    }

    public Reader getReader() {
        return reader;
    }

    public Book getBook() {
        return book;
    }

    public Date getBookOutDate() {
        return bookOutDate;
    }

    public Date getBookReturnDate() {
        return bookReturnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReaderBook that = (ReaderBook) o;
        return Objects.equals(id, that.id) && Objects.equals(reader, that.reader) && Objects.equals(book, that.book) && Objects.equals(bookOutDate, that.bookOutDate) && Objects.equals(bookReturnDate, that.bookReturnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reader, book, bookOutDate, bookReturnDate);
    }

    @Override
    public String toString() {
        return "ReaderBook{" +
                "id=" + id +
                ", reader=" + reader +
                ", book=" + book +
                ", bookOutDate=" + bookOutDate +
                ", bookReturnDate=" + bookReturnDate +
                '}';
    }
}
