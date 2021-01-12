package adventure_time.core.services.guides;

import core.requests.guides.AddGuideRequest;
import core.responses.CoreError;
import core.responses.guides.AddGuideResponse;
import database.guides.DatabaseGuides;
import domain.Guides;

import java.util.List;

public class AddGuideService {

    private final DatabaseGuides databaseGuides;
    private final AddGuideRequestValidator validator;

    public AddGuideService(DatabaseGuides databaseGuides, AddGuideRequestValidator validator) {
        this.databaseGuides = databaseGuides;
        this.validator = validator;
    }

    public AddGuideResponse addGuide(AddGuideRequest request) {
        // Validation
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new AddGuideResponse(errors);
        }
        Guides guide = new Guides(request.getGuideName(), request.getGuideEmail(),
                request.getGuidePhone());

        if (databaseGuides.add(guide)) return new AddGuideResponse();

        errors.add(new CoreError("guideName", "Guide \"" + request.getGuideName() + "\" already exists"));
        return new AddGuideResponse(errors);

    }
}