//package application_target_list.dependency_injection;
//
//import application_target_list.core.services.AddTargetService;
//import junit.framework.TestCase;
//
//import java.io.IOException;
//import java.util.List;
//
//public class DIComponentCreatorTest extends TestCase {
//
//
//
//    public void testCreate() throws IOException, ClassNotFoundException {
//        DIComponentCreator componentCreator = new DIComponentCreator();
//        ApplicationContext applicationContext = new ApplicationContext();
//        DIComponentFilter diComponentFilter = new DIComponentFilter();
//        ClassFinder classFinder = new ClassFinder();
//        List<Class> classList = classFinder.findClassesInsidePackage("application_target_list");
//        List<Class> classes = diComponentFilter.filter(classList);
//
//        componentCreator.create(applicationContext, classes);
//    }
//}