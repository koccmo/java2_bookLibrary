package team_VK.application.core.services;

import team_VK.application.core.requests.SearchBookRequest;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.services.standart_validators.AuthorFieldValidator;
import team_VK.application.core.services.standart_validators.BookIDFieldValidator;
import team_VK.application.core.services.standart_validators.TitleFieldValidator;
import team_VK.application.database.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@DIComponent
public class SearchBookServiceValidator {

    @DIDependency public AuthorFieldValidator authorFieldValidator;
    @DIDependency public TitleFieldValidator titleFieldValidator;
    @DIDependency public BookIDFieldValidator bookIDFieldValidator;

    List<CoreError> errors = new ArrayList<>();
    List<CoreError> errors1 = new ArrayList<>();
    List<CoreError> errors2 = new ArrayList<>();
    List<CoreError> errors3 = new ArrayList<>();


    public List<CoreError> validate (SearchBookRequest request){

        if(request.getBookTitle() != "") errors1 = titleFieldValidator.validate(request.getBookTitle());
        if(request.getBookAuthor() != "") errors2 = authorFieldValidator.validate(request.getBookAuthor());
        errors3 = bookIDFieldValidator.validate(request.bookIdString);
        errors = Stream.concat(
                errors1.stream(), errors2.stream())
                .collect(Collectors.toList());
        return errors;
    }



}
