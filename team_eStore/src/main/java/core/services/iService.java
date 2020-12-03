package core.services;

import core.request.CoreRequest;
import core.responses.CoreResponse;

public interface iService {
    CoreResponse execute(final CoreRequest request);
}
