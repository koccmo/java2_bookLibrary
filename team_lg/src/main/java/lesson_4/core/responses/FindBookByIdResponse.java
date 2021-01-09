package lesson_4.core.responses;

import lesson_4.core.domain.Book;

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
