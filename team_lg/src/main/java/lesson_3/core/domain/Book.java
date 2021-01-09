package lesson_3.core.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Book {

    private String bookTitle;
    private String bookAuthor;
    private BigDecimal bookPrice;
    private Long id;
    private int yearOfBookIssue;

    public Book(String bookTitle, String bookAuthor, BigDecimal bookPrice, int yearOfBookIssue) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookPrice = bookPrice;
        this.yearOfBookIssue = yearOfBookIssue;
    }

     public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public BigDecimal getBookPrice() {
        return bookPrice;
    }

    public Long getId() {
        return id;
    }

    public int getYearOfBookIssue() {
        return yearOfBookIssue;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public void setBookPrice(BigDecimal bookPrice) {
        this.bookPrice = bookPrice;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setYearOfBookIssue(int yearOfBookIssue) {
        this.yearOfBookIssue = yearOfBookIssue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(bookTitle, book.bookTitle) &&
                Objects.equals(bookAuthor, book.bookAuthor) &&
                Objects.equals(id, book.id) &&
                Objects.equals(bookPrice, book.bookPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookTitle, bookAuthor, bookPrice, id, yearOfBookIssue);
    }

    @Override
    public String toString() {
        return "Book Id="+id+" {" +
                "bookTitle='" + bookTitle + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookPrice=" + bookPrice + '\'' +
                ", yearOfBookIssue=" + yearOfBookIssue +
                "}";
    }
}