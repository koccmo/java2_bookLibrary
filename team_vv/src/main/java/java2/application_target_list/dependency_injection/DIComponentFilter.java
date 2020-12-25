package java2.application_target_list.dependency_injection;

import java.util.List;
import java.util.stream.Collectors;

public class DIComponentFilter {

    public List<Class> filter(List<Class> classes) {
        return classes.stream()
                .filter(cl -> cl.isAnnotationPresent(DIComponent.class))
                .collect(Collectors.toList());
    }

}
