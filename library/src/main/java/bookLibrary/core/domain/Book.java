package bookLibrary.core.domain;

import java.util.Objects;

public class Book {
    private String author;
    private String title;
    private Long id;

    public Book() {}
    public Book(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public void setId(Long idNumber) {
        id = idNumber;
    }

    public void setAuthor(String newAuthor) {
        author = newAuthor;
    }

    public void setTitle(String newTitle) {
        title = newTitle;
    }
    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(author, book.author) && Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, title);
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
