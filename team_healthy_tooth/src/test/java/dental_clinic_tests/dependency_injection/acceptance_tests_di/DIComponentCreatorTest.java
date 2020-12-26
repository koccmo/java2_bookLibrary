package dental_clinic_tests.dependency_injection.acceptance_tests_di;

import dental_clinic.dependency_injection.ApplicationContext;
import dental_clinic.dependency_injection.ClassFinder;
import dental_clinic.dependency_injection.DIComponentCreator;
import dental_clinic.dependency_injection.DIComponentFilter;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class DIComponentCreatorTest {

    ApplicationContext applicationContext = new ApplicationContext();
    ClassFinder classFinder = new ClassFinder();
    DIComponentFilter diComponentFilter = new DIComponentFilter();
    DIComponentCreator diComponentCreator = new DIComponentCreator();


    @Test
    public void test() throws ClassNotFoundException, IOException {
        List<Class> allClasses = classFinder.findClassesInsidePackage("dental_clinic.core.services.validators");
        List<Class> diClasses = diComponentFilter.filter(allClasses);
        diComponentCreator.create(applicationContext, diClasses);

        assertTrue(applicationContext.getBeans().isEmpty());
    }
}