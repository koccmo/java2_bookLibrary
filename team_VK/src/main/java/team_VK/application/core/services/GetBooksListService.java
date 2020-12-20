package team_VK.application.core.services;

import team_VK.application.core.domain.Book;
import team_VK.application.core.requests.GetBookListRequest;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.responses.GetBookListResponse;
import team_VK.application.database.DIComponent;
import team_VK.application.database.Database;

import java.util.Comparator;
import java.util.List;
@DIComponent
public class GetBooksListService {

    @DIDependency private Database database;
    @DIDependency private GetBooksListServiceValidator validator;


//    public GetBooksListService(Database database, GetBooksListServiceValidator validator) {
//        this.database = database;
//        this.validator = validator;
//    }

    public GetBookListResponse getBooksList(GetBookListRequest request) {

        List<CoreError> errors = validator.validate(request);


        if (errors.size() == 0) {
            System.out.println("Book list:");
//            for (Book book : database.getListBooks()) {
//                System.out.println(book);
//            }

            switch (request.getSortingCriteria()) {
                case 1:
                    database.getListBooks().stream()
                            .sorted(Comparator.comparing(Book::getBookTitle))
                            .forEach(book -> System.out.println(book.toString()));
                    break;

                case 2:
                    database.getListBooks().stream()
                            .sorted(Comparator.comparing(Book::getBookAuthor))
                            .forEach(book -> System.out.println(book.toString()));
                    break;
                case 3:
                    database.getListBooks().stream()
                            .sorted((book1, book2) -> ((Long) book1.getID()).compareTo(book2.getID()))
                            .forEach(book -> System.out.println(book.toString()));
                    break;

            }
            System.out.println("End of list");

        }
        return new GetBookListResponse(errors);

    }
}