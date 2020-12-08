package internet_store_tests.dependency_injection;

import internet_store.dependency_injection.ClassFinder;
import internet_store.dependency_injection.DIComponentFiler;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class DIComponentFilterTest {

    ClassFinder classFinder = new ClassFinder();
    DIComponentFiler diComponentFiler = new DIComponentFiler();

    @Test
    public void testPackageDatabase() throws ClassNotFoundException, IOException {
        List<Class> classes = classFinder.findClassesInsidePackage("internet_store.database");
        List<Class> annotatedClasses = diComponentFiler.filter(classes);

        assertTrue(annotatedClasses.size() == 2);
        assertTrue(annotatedClasses.get(1).getName().equals("internet_store.database.product.ProductDatabaseImpl"));
    }
}
