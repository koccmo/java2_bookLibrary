package core.responses.guides;

import core.responses.CoreError;

import java.util.List;

public class RemoveGuideResponse extends CoreResponseGuide {

    public RemoveGuideResponse() {}

    public RemoveGuideResponse(List<CoreError> errors) {
        super(errors);
    }
}
