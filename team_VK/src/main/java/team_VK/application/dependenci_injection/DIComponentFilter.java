package team_VK.application.dependenci_injection;

import team_VK.application.database.DIComponent;

import java.util.List;
import java.util.stream.Collectors;

public class DIComponentFilter {

    public List<Class> filter (List<Class> classes){


        return classes.stream()
                .filter(cl -> cl.isAnnotationPresent(DIComponent.class))
                .collect(Collectors.toUnmodifiableList());
    }

}
