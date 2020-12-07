package lesson_6;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private final Map<Class<?>, Object> beans = new HashMap<>();

    public ApplicationContext() {
    }

    public void addBean(Class<?> beanClass, Object beanInstance) {
        beans.put(beanClass, beanInstance);
        Class<?>[] instanceInterfaces = beanClass.getInterfaces();
        for (Class<?> instanceInterface : instanceInterfaces) {
            Object instance = getBean(instanceInterface);
            if (instance == null) {
                beans.put(instanceInterface, beanInstance);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<?> c) {
        return (T) beans.get(c);
    }
}