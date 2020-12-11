package dental_clinic_tests.dependency_injection.acceptance_tests_di;

import dental_clinic.dependency_injection.ClassFinder;
import dental_clinic.dependency_injection.DIComponentFilter;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class DIComponentFilterTest {

    ClassFinder classFinder = new ClassFinder();
    DIComponentFilter diComponentFilter = new DIComponentFilter();

    @Test
    public void testPackageDatabase () throws ClassNotFoundException, IOException {
        List<Class> classes = classFinder.findClassesInsidePackage("dental_clinic.database");
        List<Class> classesWithAnnotation = diComponentFilter.filter(classes);

        assertTrue(classesWithAnnotation.size() == 0);
    }

}