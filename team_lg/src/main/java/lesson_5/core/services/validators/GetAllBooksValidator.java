package lesson_5.core.services.validators;

import lesson_5.core.requests.GetAllBooksRequest;
import lesson_5.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class GetAllBooksValidator {
    public List<CoreError> validate (GetAllBooksRequest getAllBooksRequest){
        return new ArrayList<>();
    }
}
