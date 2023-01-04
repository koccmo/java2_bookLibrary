package bookLibrary.dependency_injection;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ClassFinderTest {

    @Test
    public void findAllClassesInPackageThenSuccess() throws IOException, ClassNotFoundException {
        ClassFinder classFinder = new ClassFinder();
        List<Class> classes = classFinder.findClassesInsidePackage("bookLibrary.core.dataBase");
        assertEquals(4, classes.size());
    }
}