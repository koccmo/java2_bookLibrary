//package application_target_list.dependency_injection;
//
//import junit.framework.TestCase;
//
//import java.io.IOException;
//import java.util.List;
//
//public class ClassFinderTest extends TestCase {
//
//    ClassFinder classFinder = new ClassFinder();
//
//    public void testFindClassesInsidePackage_v1() throws IOException, ClassNotFoundException {
//        List<Class> classList = classFinder.findClassesInsidePackage("application_target_list.console_ui.actions");
//        assertEquals(classList.size(), 12);
//
//        classList.forEach(aClass -> System.out.println(aClass.getName()));
//    }
//
//    public void testFindClassesInsidePackage_v2() throws IOException, ClassNotFoundException {
//        List<Class> classList = classFinder.findClassesInsidePackage("application_target_list");
//        assertEquals(classList.size(), 87);
//
//        classList.forEach(aClass -> System.out.println(aClass.getName()));
//    }
//
//    public void testFindClassesInsidePackage_v3() throws IOException, ClassNotFoundException {
//        List<Class> classList = classFinder.findClassesInsidePackage("application_target_list.core.database");
//        classList.forEach(aClass -> System.out.println(aClass.getName()));
//        assertEquals(classList.size(), 4);
//    }
//}