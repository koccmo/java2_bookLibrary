package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.UserDatabase;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.requests.Ordering;
import java2.application_target_list.core.requests.Paging;
import java2.application_target_list.core.requests.user.SearchUsersByFirstNameRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.SearchUserByFirstNameResponse;
import java2.application_target_list.core.validators.user.SearchUserByFirstNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchUserByFirstNameService {

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Autowired private UserDatabase userDatabase;
    @Autowired private SearchUserByFirstNameValidator searchUserByFirstNameValidator;

    public SearchUserByFirstNameResponse execute(SearchUsersByFirstNameRequest request){
        List<CoreError> errors = searchUserByFirstNameValidator.validate(request);

        if (!errors.isEmpty()){
            return new SearchUserByFirstNameResponse(errors, null);
        }

        List<User> users = userDatabase.findUserByFirstName(request.getFirstName());
        users = order(users, request.getOrdering());
        users = paging(users, request.getPaging());

        return new SearchUserByFirstNameResponse(null, users);

    }

    private List<User> order(List<User> users, Ordering ordering) {

        if (orderingEnabled && ordering != null) {
            if (isNeedOrderingByFirstName(ordering)) {
                if (isNeedOrderingDescending(ordering)) {
                    return sortByFirstNameDescending(users);
                }
                return sortByFirstNameAscending(users);
            }

            if (isNeedOrderingByLastName(ordering)) {
                if (isNeedOrderingDescending(ordering)) {
                    return sortByLastNameDescending(users);
                }
                return sortByLastNameAscending(users);
            }


        }
        return users;
    }

    private boolean isNeedOrderingDescending(Ordering ordering){
        return ordering.getOrderDirection().equals("DESCENDING");
    }

    private boolean isNeedOrderingByFirstName(Ordering ordering){
        return ordering.getOrderBy().equals("first name");
    }

    private boolean isNeedOrderingByLastName(Ordering ordering){
        return ordering.getOrderBy().equals("last name");
    }

    private List<User> sortByFirstNameDescending(List<User> users){
        return users.stream()
                .sorted(Comparator.comparing(User::getFirstName).reversed())
                .collect(Collectors.toList());
    }

    private List<User> sortByLastNameDescending(List<User> users){
        return users.stream()
                .sorted(Comparator.comparing(User::getLastName).reversed())
                .collect(Collectors.toList());
    }


    private List<User> sortByFirstNameAscending(List<User> users){
        return users.stream()
                .sorted(Comparator.comparing(User::getFirstName))
                .collect(Collectors.toList());
    }

    private List<User> sortByLastNameAscending(List<User> users){
        return users.stream()
                .sorted(Comparator.comparing(User::getLastName))
                .collect(Collectors.toList());
    }

    private List<User> paging(List<User> users, Paging paging) {
        if (pagingEnabled && paging != null) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return users.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return users;
        }
    }

}
