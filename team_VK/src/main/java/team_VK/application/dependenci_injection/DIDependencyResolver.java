package team_VK.application.dependenci_injection;

import team_VK.application.ApplicationContext;
import team_VK.application.core.services.DIDependency;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DIDependencyResolver {

    public void resolve(ApplicationContext applicationContext, List<Class> diComponents) {

        diComponents.forEach(diComponent -> {
            Object diInstance = applicationContext.getBean(diComponent);
            List<Field> diFields = findFieldsWithDIDependencyAnnotation(diComponent);
            diFields.forEach(diField ->{
                tryToSetDependency(applicationContext, diInstance, diField);
            });
        });



    }

    private void tryToSetDependency(ApplicationContext applicationContext, Object diInstance, Field diField) {

        Class fieldType = diField.getType();
        Object fieldInstance = applicationContext.getBean(fieldType);
        if (fieldInstance == null)
            throw new RuntimeException("No dependency found");
        else {
            setValueToPrivateField(diInstance, diField, fieldInstance);
        }

    }

    private void setValueToPrivateField(Object diInstance, Field diField, Object fieldInstance) {

        try{
            diField.setAccessible(true);
            diField.set(diInstance,fieldInstance);
        }catch (IllegalAccessException e) {throw new RuntimeException("Cannot resolve dependency");}

    }

    private List<Field> findFieldsWithDIDependencyAnnotation(Class diComponent) {

        return Arrays.stream(diComponent.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(DIDependency.class))
                .collect(Collectors.toList());
    }

}
