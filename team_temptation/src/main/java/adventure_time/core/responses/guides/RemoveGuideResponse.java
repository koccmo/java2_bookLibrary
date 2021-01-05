package adventure_time.core.responses.guides;

import adventure_time.core.responses.CoreError;

import java.util.List;

public class RemoveGuideResponse extends CoreResponseGuide {

    public RemoveGuideResponse() {}

    public RemoveGuideResponse(List<CoreError> errors) {
        super(errors);
    }
}
