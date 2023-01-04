package bookLibrary.core.domain;

import jakarta.persistence.*;

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





}
