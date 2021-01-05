package adventure_time.core.responses.guides;

import adventure_time.core.responses.CoreError;

import java.util.List;

public class AddGuideResponse extends CoreResponseGuide {

    public AddGuideResponse() {}

    public AddGuideResponse(List<CoreError> errors) {
        super(errors);
    }
}
