package core.responses.guides;

import core.responses.CoreError;

import java.util.List;

public class AddGuideResponse extends CoreResponseGuide {

    public AddGuideResponse() {}

    public AddGuideResponse(List<CoreError> errors) {
        super(errors);
    }
}
