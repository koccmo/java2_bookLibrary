package bookLibrary.core.service;

import bookLibrary.core.dataBase.DataBase;
import bookLibrary.core.request.GetBookIdRequest;
import bookLibrary.core.response.CoreError;
import bookLibrary.core.response.GetBookIdResponse;
import bookLibrary.core.service.validators.GetBookIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class GetBookIdService {
    @Autowired
    private DataBase dataBase;
    @Autowired
    private GetBookIdValidator validator;

    public GetBookIdResponse execute(GetBookIdRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (errors.isEmpty()) {
            GetBookIdResponse response = new GetBookIdResponse(String.valueOf(
                    dataBase.getBookId(request.getAuthor(), request.getTitle())));
            return response;
        } else {
            GetBookIdResponse response = new GetBookIdResponse(errors);
            return response;
        }
    }
}
