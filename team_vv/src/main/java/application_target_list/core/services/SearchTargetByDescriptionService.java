package application_target_list.core.services;

import application_target_list.core.requests.Paging;
import application_target_list.core.validators.SearchTargetByDescriptionValidator;
import application_target_list.core.database.Database;
import application_target_list.core.database.Target;
import application_target_list.core.requests.SearchTargetByDescriptionRequest;
import application_target_list.core.responses.CoreError;
import application_target_list.core.responses.SearchTargetByDescriptionResponse;


import java.util.List;
import java.util.stream.Collectors;

public class SearchTargetByDescriptionService {

    private Database database;
    private SearchTargetByDescriptionValidator validator;

    public SearchTargetByDescriptionService(Database database, SearchTargetByDescriptionValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public SearchTargetByDescriptionResponse execute(SearchTargetByDescriptionRequest request){
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new SearchTargetByDescriptionResponse(errors, null);
        }



        List<Target> targets = database.findByTargetDescription(request.getDescription());
        targets = paging(targets, request.getPaging());

        return new SearchTargetByDescriptionResponse(null, targets);
    }


    private List<Target> paging(List<Target> targets, Paging paging) {
        if (paging != null) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return targets.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return targets;
        }
    }
}
