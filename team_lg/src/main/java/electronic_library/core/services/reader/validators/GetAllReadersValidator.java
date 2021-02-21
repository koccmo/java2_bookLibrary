package electronic_library.core.services.reader.validators;

import electronic_library.core.requests.reader.GetAllReadersRequest;
import electronic_library.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetAllReadersValidator {
    public List<CoreError> validate (GetAllReadersRequest getAllReadersRequest){
        return new ArrayList<>();
    }
}
