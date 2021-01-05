package adventure_time.dependencies;

import java.util.HashMap;
import java.util.Map;

public class EventApplicationContext {

    private Map<Class, Object> bean = new HashMap<>();

    public <T extends Object> T getBean(Class c) {
        return (T) bean.get(c);
    }

    public EventApplicationContext() {}

//    public EventApplicationContext() {
//        **bean.put(EventDatabase.class, new InMemoryEvents());
//
//        **bean.put(StartUpEventUIAction.class, new StartUpEventUIAction(getBean(EventDatabase.class)));
//
//        **bean.put(AddEventRequestValidator.class, new AddEventRequestValidator());
//        **bean.put(AddEventService.class, new AddEventService(getBean(EventDatabase.class), getBean(AddEventRequestValidator.class)));
//        **bean.put(AddEventUIAction.class, new AddEventUIAction(getBean(AddEventService.class)));
//
//        **bean.put(RemoveEventRequestValidator.class, new RemoveEventRequestValidator());
//        **bean.put(RemoveEventService.class, new RemoveEventService(getBean(EventDatabase.class), getBean(RemoveEventRequestValidator.class)));
//        **bean.put(RemoveEventUIAction.class, new RemoveEventUIAction(getBean(RemoveEventService.class)));
//
//        **bean.put(SearchEventRequestValidator.class, new SearchEventRequestValidator());
//        **bean.put(SearchEventService.class, new SearchEventService(getBean(EventDatabase.class), getBean(SearchEventRequestValidator.class)));
//        **bean.put(SearchEventUIAction.class, new SearchEventUIAction(getBean(SearchEventService.class)));
//
//        **bean.put(DisplayEventListService.class, new DisplayEventListService(getBean(EventDatabase.class)));
//        **bean.put(DisplayEventUIAction.class, new DisplayEventUIAction(getBean(DisplayEventListService.class)));
//
//        **bean.put(ExitEventUIAction.class, new ExitEventUIAction());
//    }

    public void addBean(Class beanClass, Object beanInstance) {
        bean.put(beanClass, beanInstance);
        Class[] instanceInterfaces = beanClass.getInterfaces();
        for (int i = 0; i < instanceInterfaces.length; i++) {
            Class instanceInterface = instanceInterfaces[i];
            Object instance = getBean(instanceInterface);
            if (instance == null) {
                bean.put(instanceInterface, beanInstance);
            }
        }
    }

}
