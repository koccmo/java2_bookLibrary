package adventure_time.core.responses.guides;

import core.responses.CoreError;
import core.responses.CoreResponse;
import domain.Guides;

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