package application_target_list.core.services;

import application_target_list.core.database.Database;
import application_target_list.core.database.Target;
import application_target_list.core.requests.Paging;
import application_target_list.core.requests.SearchTargetByNameRequest;
import application_target_list.core.responses.CoreError;
import application_target_list.core.responses.SearchTargetByNameResponse;
import application_target_list.core.validators.SearchTargetByNameValidator;

import java.util.List;
import java.util.stream.Collectors;

public class SearchTargetByNameService {

   private final Database database;
   private final SearchTargetByNameValidator validator;

    public SearchTargetByNameService(Database database, SearchTargetByNameValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public SearchTargetByNameResponse execute(SearchTargetByNameRequest request){
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new SearchTargetByNameResponse(errors, null);
        }

        List<Target> targets = database.findByTargetName(request.getName());
        targets = paging(targets, request.getPaging());

        return new SearchTargetByNameResponse(null, targets);
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
