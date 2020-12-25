package internet_store_tests.dependency_injection;

import internet_store.dependency_injection.ClassFinder;
import internet_store.dependency_injection.DIComponentFilter;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class DIComponentFilterTest {

    ClassFinder classFinder = new ClassFinder();
    DIComponentFilter diComponentFilter = new DIComponentFilter();

    @Test
    public void testPackageDatabase() throws ClassNotFoundException, IOException {
        List<Class> classes = classFinder.findClassesInsidePackage("internet_store.database");
        List<Class> annotatedClasses = diComponentFilter.filter(classes);
        assertTrue(annotatedClasses.size() == 0);
    }
}
