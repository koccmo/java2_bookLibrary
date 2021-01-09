package lesson_4.core.services.validators;

import lesson_4.core.requests.GetAllBooksRequest;
import lesson_4.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class GetAllBooksValidator {
    public List<CoreError> validate (GetAllBooksRequest getAllBooksRequest){
        return new ArrayList<>();
    }
}
