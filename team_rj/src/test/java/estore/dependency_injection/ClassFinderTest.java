package estore.dependency_injection;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ClassFinderTest {
    ClassFinder classFinder = new ClassFinder();

    @Test
    public void ShouldReturnListWithFilesInGivenFolder() throws IOException, ClassNotFoundException{
        List<Class> classList = classFinder.findClassesInsidePackage("estore.core.domain");
        assertEquals(classList.size(), 2);
        classList.forEach(aClass ->
            System.out.println(aClass.getName())
        );
    }
}