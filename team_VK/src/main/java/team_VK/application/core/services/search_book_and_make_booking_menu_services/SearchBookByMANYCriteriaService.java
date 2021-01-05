package team_VK.application.core.services.search_book_and_make_booking_menu_services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team_VK.application.core.domain.Book;
import team_VK.application.core.requests.SearchBookRequest;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.responses.SearchBookResponse;
import team_VK.application.core.services.additional_functions.ResultBookListPrinter;
import team_VK.application.core.services.validators.SearchBookServiceValidator;
import team_VK.application.database.Database;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchBookByMANYCriteriaService {
    @Autowired
    private Database database;
    @Autowired public SearchBookServiceValidator validator;
    @Autowired public ResultBookListPrinter resultBookListPrinter;


    public SearchBookResponse searchBook(SearchBookRequest request) {

        List<CoreError> errors = validator.validate(request);

        if(errors.size() == 0){
            List<Book> resultList = database.getListBooks().stream()
                    .filter(book -> {
                        if(!request.getBookTitle().equals("") && request.getBookTitle() != null) return book.getBookTitle().equals(request.getBookTitle());
                        else return true;
                    })
                    .filter((Book book) -> {
                        if(!request.getBookAuthor().equals("") && request.getBookAuthor() != null) return book.getBookAuthor().equals(request.getBookAuthor());
                       else return true;
                    })
                    .filter(book -> {
                       if(!request.getBookIdString().equals(""))
                           return book.getID() == Integer.parseInt(request.getBookIdString());
                       else return true;
                    })
                    .collect(Collectors.toList());
            resultBookListPrinter.execute(resultList);
        }
        return new SearchBookResponse(errors);
    }
}
