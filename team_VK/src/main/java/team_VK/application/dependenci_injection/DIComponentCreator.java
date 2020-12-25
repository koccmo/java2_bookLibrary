package team_VK.application.dependenci_injection;

import team_VK.application.ApplicationContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DIComponentCreator {

    public void create(ApplicationContext applicationContext, List<Class> diComponents) {

        for (Class diComponent : diComponents) {
            Optional<Constructor> defaultConstructorOpt = getDefaultConstructor(diComponent);
            if (defaultConstructorOpt.isPresent()) {
                Object diComponentInstance = createInctanceUsingdefaultConstructor(defaultConstructorOpt.get());
                applicationContext.addBean(diComponent, diComponentInstance);
            } else {
                throw new RuntimeException("Class doesn't have default constructor" + diComponent.getClass());
            }
        }

    }

    private Object createInctanceUsingdefaultConstructor(Constructor defaultConstructor) {
        try {
            return defaultConstructor.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException();
        } catch (IllegalAccessException e) {
            throw new RuntimeException();
        } catch (InvocationTargetException e) {
            throw new RuntimeException();
        }

    }

    private Optional<Constructor> getDefaultConstructor(Class diComponents) {
        return Arrays.stream(diComponents.getConstructors())
                .filter(constructor -> constructor.getParameterCount() == 0)
                .findFirst();
    }

}
