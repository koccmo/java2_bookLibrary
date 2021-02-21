package lesson_12.core.services.reader.validators;

import lesson_12.core.requests.reader.GetAllReadersRequest;
import lesson_12.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetAllReadersValidator {
    public List<CoreError> validate (GetAllReadersRequest getAllReadersRequest){
        return new ArrayList<>();
    }
}
