package internet_store.application.dependency_injection;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ClassFinderTest {

  /*  private ClassFinder classFinder;

    @Before
    public void setUp() {
        classFinder = new ClassFinder();
    }

    @Test
    public void findClassesInsidePackage() throws IOException, ClassNotFoundException {
        List<Class> classes = classFinder.findClassesInsidePackage("internet_store.application");
        classes.forEach(System.out::println);
    }
*/

/* Viktor's variant
    @Test
    public void test() throws IOException, ClassNotFoundException {
        ClassFinder finder = new ClassFinder();
        List<Class> classes = finder.findClassesInsidePackage("internet_store.application");
        classes.forEach(aClass -> {
            System.out.println(aClass.getName());
        });
    }*/

}