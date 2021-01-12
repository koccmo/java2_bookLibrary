package adventure_time.core.responses.guides;

import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.CoreResponse;

import java.util.List;

public class RemoveGuideResponse extends CoreResponse {

    private boolean successRemoval;

    public RemoveGuideResponse() {}

    public RemoveGuideResponse(List<CoreError> errors) {
        super(errors);
    }

    public RemoveGuideResponse(boolean successRemoval) {
        this.successRemoval = successRemoval;
    }

    public boolean isSuccessRemoval() {
        return this.successRemoval;
    }
}
