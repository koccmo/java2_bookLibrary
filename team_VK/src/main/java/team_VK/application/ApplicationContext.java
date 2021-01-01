package team_VK.application;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private Map<Class, Object> beans = new HashMap<>();

    public ApplicationContext() {    }

    public <T extends Object> T getBean(Class c) {
        return (T) beans.get(c);
    }

    public void addBean (Class diClass, Object diComponentInstance){
        beans.put(diClass, diComponentInstance);

        Class[] instanceInterfaces = diClass.getInterfaces();
        for (int i = 0; i < instanceInterfaces.length; i++) {
            Class instanceInterface = instanceInterfaces[i];
            Object instance = getBean(instanceInterface);
            if (instance == null){
                beans.put(instanceInterface, diComponentInstance);
            }
        }
    }

}
