package dental_clinic.dependency_injection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DIComponentCreator {

    public void create(ApplicationContext applicationContext, List<Class> diComponents){

        for (int i = 0; i < diComponents.size(); i++){
            Optional<Constructor> defaultConstructor = getDefaultConstructor(diComponents.get(i));
            if (defaultConstructor.isPresent()) {
                Object beanInstance = createInstanceUsingDefaultConstructor(defaultConstructor.get());
                applicationContext.addBean(diComponents.get(i), beanInstance);
            }
        }
    }

    private Optional<Constructor> getDefaultConstructor(Class diComponent){
        return Arrays.stream(diComponent.getConstructors())
                .filter(constructor -> constructor.getParameterCount() == 0)
                .findAny();
    }

    private Object createInstanceUsingDefaultConstructor (Constructor constructor){
        try {
            return constructor.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}
