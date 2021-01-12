package adventure_time.core.responses.guides;

import adventure_time.core.responses.CoreError;
import adventure_time.core.responses.CoreResponse;
import adventure_time.core.domain.Guides;

import java.util.List;

public class SearchGuideResponse extends CoreResponse {

    private final List<Guides> guides;

    public SearchGuideResponse(List<Guides> guides, List<CoreError> errors) {
        super(errors);
        this.guides = guides;
    }

    public List<Guides> getGuides() {
        return guides;
    }
}