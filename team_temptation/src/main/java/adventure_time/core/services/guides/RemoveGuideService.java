package adventure_time.core.services.guides;

import core.requests.guides.RemoveGuideRequest;
import core.responses.CoreError;
import core.responses.guides.RemoveGuideResponse;
import database.guides.DatabaseGuides;

import java.util.List;

public class RemoveGuideService {
    private final DatabaseGuides databaseGuides;
    private final RemoveGuideRequestValidator validator;

    public RemoveGuideService(DatabaseGuides databaseGuides, RemoveGuideRequestValidator validator) {
        this.databaseGuides = databaseGuides;
        this.validator = validator;
    }

    public RemoveGuideResponse removeGuide(RemoveGuideRequest request) {

        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new RemoveGuideResponse(errors);
        }

        if (databaseGuides.remove(request.getGuideName())) return new RemoveGuideResponse();

        errors.add(new CoreError("guideName", "Guide \"" + request.getGuideName() + "\" was not found."));
        return new RemoveGuideResponse(errors);
    }
}
