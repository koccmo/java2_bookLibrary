package lesson_10.core.services.validators;

import lesson_10.core.requests.GetAllBooksRequest;
import lesson_10.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetAllBooksValidator {
    public List<CoreError> validate (GetAllBooksRequest getAllBooksRequest){
        return new ArrayList<>();
    }
}
