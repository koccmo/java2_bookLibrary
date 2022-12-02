package bookLibrary.dependency_injection;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DIComponentFilterTest {
    ClassFinder classFinder = new ClassFinder();
    DIComponentFilter diComponentFilter = new DIComponentFilter();

    @Test
    public void searchClassesWithDiComponentAnnotation() throws IOException, ClassNotFoundException {
        List<Class> classes = classFinder.findClassesInsidePackage("bookLibrary.core.dataBase");
        List<Class> classesWithAnnotation = diComponentFilter.findDIComponentClasses(classes);
        assertEquals(0, classesWithAnnotation.size());
    }
}