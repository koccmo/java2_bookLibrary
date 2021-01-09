package lesson_5.core.requests;

import java.math.BigDecimal;

public class DeleteBookRequest {

    private String bookTitle;
    private String bookAuthor;
    private BigDecimal bookPrice;
    private int yearOfBookIssue;

    public DeleteBookRequest(String bookTitle, String bookAuthor, BigDecimal bookPrice, int yearOfBookIssue) {
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

    public int getYearOfBookIssue() {
        return yearOfBookIssue;
    }


}
