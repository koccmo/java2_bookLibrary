package book_library.dependency_injection;

import com.sun.jdi.ClassNotLoadedException;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class ClassFinderTest {

    @Test
    public void Test() throws IOException, ClassNotFoundException {
        ClassFinder finder = new ClassFinder();
        List<Class> classes = finder.findClassesInsidePackage("book_library");
        classes.forEach(aClass -> {
            System.out.println(aClass.getName());
        });
    }

}