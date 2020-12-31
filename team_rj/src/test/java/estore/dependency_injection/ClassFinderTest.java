package estore.dependency_injection;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ClassFinderTest {
    ClassFinder classFinder = new ClassFinder();

    @Test
    public void ShouldReturnListWithFilesInGivenFolder() throws IOException, ClassNotFoundException{
        List<Class> classList = classFinder.findClassesInsidePackage("estore.domain");
        assertTrue(classList.size() == 3);
        classList.forEach(aClass -> {
            System.out.println(aClass.getName());
        });
    }
}