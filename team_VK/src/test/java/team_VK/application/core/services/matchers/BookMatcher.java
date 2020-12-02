package team_VK.application.core.services.matchers;

import org.mockito.ArgumentMatcher;
import team_VK.application.core.domain.Book;

public class BookMatcher implements ArgumentMatcher<Book> {

    String bookTitle;
    String bookAuthor;
    int bookingDays;

    public BookMatcher(String bookTitle, String bookAuthor, int bookingDays) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookingDays = bookingDays;
    }

    @Override
    public boolean matches(Book book) {
        return book.getBookTitle().equals(bookTitle)  &&
                book.getBookAuthor().equals(bookAuthor) &&
                book.getBookingDurationPermitted() == bookingDays;
    }
}
