package electronic_library.core.services.validators;

import electronic_library.core.requests.GetAllBooksRequest;
import electronic_library.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetAllBooksValidator {
    public List<CoreError> validate (GetAllBooksRequest getAllBooksRequest){
        return new ArrayList<>();
    }
}
