package adventure_time.core.responses.guides;

import core.responses.CoreError;
import core.responses.CoreResponse;

import java.util.List;

public class AddGuideResponse extends CoreResponse {

    public AddGuideResponse() {}

    public AddGuideResponse(List<CoreError> errors) {
        super(errors);
    }
}
