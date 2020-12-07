package lesson_6;

import lesson_6.annotation.DIComponent;
import java.util.List;
import java.util.stream.Collectors;

class DIComponentFilter {
    public List<Class<?>> filter(List<Class<?>> classes) {
        return classes.stream()
                .filter(cl -> cl.isAnnotationPresent(DIComponent.class))
                .collect(Collectors.toList());
    }
}