package application_target_list.core.services;

import application_target_list.core.database.Database;
import application_target_list.core.database.Target;
import application_target_list.core.requests.Ordering;
import application_target_list.core.requests.Paging;
import application_target_list.core.requests.SearchTargetByNameRequest;
import application_target_list.core.responses.CoreError;
import application_target_list.core.responses.SearchTargetByNameResponse;
import application_target_list.core.validators.SearchTargetByNameValidator;

import java.util.Comparator;
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
        targets = order(targets, request.getOrdering());

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

    private List<Target> order(List<Target> targets, Ordering ordering) {

        if (ordering != null) {
            if (isNeedOrderingByName(ordering)) {
                if (isNeedOrderingDescending(ordering)) {
                    return sortByNameDescending(targets);
                }
                return sortByNameAscending(targets);
            }

            if (isNeedOrderingByDescription(ordering)) {
                if (isNeedOrderingDescending(ordering)) {
                    return sortByDescriptionDescending(targets);
                }
                return sortByDescriptionAscending(targets);
            }

            if (isNeedOrderingByDeadline(ordering)) {
                if (isNeedOrderingDescending(ordering)) {
                    return sortByDeadlineDescending(targets);
                }
                return sortByDeadlineAscending(targets);
            }

        }
        return targets;
    }

    private boolean isNeedOrderingDescending(Ordering ordering){
        return ordering.getOrderDirection().equals("DESCENDING");
    }

    private boolean isNeedOrderingByName(Ordering ordering){
        return ordering.getOrderBy().equals("name");
    }

    private boolean isNeedOrderingByDescription(Ordering ordering){
        return ordering.getOrderBy().equals("description");
    }

    private boolean isNeedOrderingByDeadline(Ordering ordering){
        return ordering.getOrderBy().equals("deadline");
    }

    private List<Target> sortByNameDescending(List<Target> targets){
        return targets.stream()
                .sorted(Comparator.comparing(Target::getName).reversed())
                .collect(Collectors.toList());
    }

    private List<Target> sortByDescriptionDescending(List<Target> targets){
        return targets.stream()
                .sorted(Comparator.comparing(Target::getDescription).reversed())
                .collect(Collectors.toList());
    }

    private List<Target> sortByDeadlineDescending(List<Target> targets){
        return targets.stream()
                .sorted(Comparator.comparing(Target::getDeadline).reversed())
                .collect(Collectors.toList());
    }

    private List<Target> sortByNameAscending(List<Target> targets){
        return targets.stream()
                .sorted(Comparator.comparing(Target::getName))
                .collect(Collectors.toList());
    }

    private List<Target> sortByDescriptionAscending(List<Target> targets){
        return targets.stream()
                .sorted(Comparator.comparing(Target::getDescription))
                .collect(Collectors.toList());
    }

    private List<Target> sortByDeadlineAscending(List<Target> targets){
        return targets.stream()
                .sorted(Comparator.comparing(Target::getDeadline))
                .collect(Collectors.toList());
    }

}
