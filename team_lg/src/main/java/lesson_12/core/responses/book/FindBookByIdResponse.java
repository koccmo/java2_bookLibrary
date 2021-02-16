package lesson_12.core.responses.book;

import lesson_12.core.domain.Book;
import lesson_12.core.responses.CoreError;
import lesson_12.core.responses.CoreResponse;

import java.util.List;
import java.util.Optional;

public class FindBookByIdResponse extends CoreResponse {

    private Optional<Book> findBookById;

    public FindBookByIdResponse(Optional<Book> findBookById) {
        this.findBookById = findBookById;
    }

    public FindBookByIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public Optional<Book> getFindBookById() {
        return findBookById;
    }

}
