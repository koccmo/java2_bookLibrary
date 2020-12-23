//package application_target_list.dependency_injection;
//
//import junit.framework.TestCase;
//
//import java.io.IOException;
//import java.util.List;
//
//@DIComponent
//public class DIComponentFilterTest extends TestCase {
//
//    DIComponentFilter filter = new DIComponentFilter();
//    ClassFinder finder = new ClassFinder();
//
//    public void testFilter() throws IOException, ClassNotFoundException {
//        List<Class> classList = finder.findClassesInsidePackage("application_target_list");
//        List<Class> classes = filter.filter(classList);
//        assertEquals(classes.size(), 29);
//
//        classes.forEach(aClass -> System.out.println(aClass.getName()));
//    }
//}