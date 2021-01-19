package electronic_library.dependency_injection;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ClassFinderTest {

    @Test
    public void Test() throws IOException, ClassNotFoundException {
        ClassFinder finder = new ClassFinder();
        List<Class> classes = finder.findClassesInsidePackage("electronic_library");
        classes.forEach(aClass -> {
            System.out.println(aClass.getName());
        });
    }
}