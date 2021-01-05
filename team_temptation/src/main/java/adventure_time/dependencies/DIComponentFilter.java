package adventure_time.dependencies;

import java.util.List;
import java.util.stream.Collectors;

public class DIComponentFilter {

    public List<Class> filter(List<Class> classes) {
        return classes.stream()
                .filter(cl -> cl.isAnnotationPresent(DIComponent.class))    // проверка на наличие аннотации
                .collect(Collectors.toList());
    }
}
