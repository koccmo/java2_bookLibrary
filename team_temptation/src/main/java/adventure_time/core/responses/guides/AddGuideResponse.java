package adventure_time.core.responses.guides;

import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.CoreResponse;

import java.util.List;

public class AddGuideResponse extends CoreResponse {

    public AddGuideResponse() {}

    public AddGuideResponse(List<CoreError> errors) {
        super(errors);
    }
}
