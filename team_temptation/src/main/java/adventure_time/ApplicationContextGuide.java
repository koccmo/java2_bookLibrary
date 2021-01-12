//package adventure_time;
//
//import adventure_time.core.services.guides.*;
//import adventure_time.database.guides.*;
//import adventure_time.ui.guides.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class ApplicationContextGuide {
//
//    private final Map<Class, Object> bean = new HashMap<>();
//
//    public <T extends Object> T getBean(Class c) {
//        return (T) bean.get(c);
//    }
//
//    public ApplicationContextGuide() {
//        bean.put(DatabaseGuides.class, new InMemoryGuides());
//
//        bean.put(StartUpGuideUIAction.class, new StartUpGuideUIAction(getBean(DatabaseGuides.class)));
//
//        bean.put(AddGuideRequestValidator.class, new AddGuideRequestValidator());
//        bean.put(AddGuideService.class, new AddGuideService(getBean(DatabaseGuides.class), getBean(AddGuideRequestValidator.class)));
//        bean.put(AddGuideUIAction.class, new AddGuideUIAction(getBean(AddGuideService.class)));
//
//        bean.put(RemoveGuideRequestValidator.class, new RemoveGuideRequestValidator());
//        bean.put(RemoveGuideService.class, new RemoveGuideService(getBean(DatabaseGuides.class), getBean(RemoveGuideRequestValidator.class)));
//        bean.put(RemoveGuideUIAction.class, new RemoveGuideUIAction(getBean(RemoveGuideService.class)));
//
//        bean.put(DisplayGuideListService.class, new DisplayGuideListService(getBean(DatabaseGuides.class)));
//        bean.put(DisplayGuideUIAction.class, new DisplayGuideUIAction(getBean(DisplayGuideListService.class)));
//
//        bean.put(ExitGuideUIAction.class, new ExitGuideUIAction());
//
//    }
//}