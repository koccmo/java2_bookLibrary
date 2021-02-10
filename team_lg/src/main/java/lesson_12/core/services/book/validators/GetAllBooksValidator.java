package lesson_12.core.services.book.validators;

import lesson_12.core.requests.book.GetAllBooksRequest;
import lesson_12.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetAllBooksValidator {
    public List<CoreError> validate (GetAllBooksRequest getAllBooksRequest){
        return new ArrayList<>();
    }
}
