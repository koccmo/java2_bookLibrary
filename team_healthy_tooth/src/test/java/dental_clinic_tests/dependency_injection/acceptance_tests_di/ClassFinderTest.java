package dental_clinic_tests.dependency_injection.acceptance_tests_di;

import dental_clinic.dependency_injection.ClassFinder;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class ClassFinderTest {

    ClassFinder classFinder = new ClassFinder();

    @Test
    public void testReceivedFiles() throws IOException, ClassNotFoundException{

        List<Class> classList = classFinder.findClassesInsidePackage("dental_clinic.console_ui");

        //assertTrue(classList.size() == 17);
    }

    @Test
    public void testReceivedFiles2() throws IOException, ClassNotFoundException{

        List<Class> classList = classFinder.findClassesInsidePackage("dental_clinic.database");

        //assertTrue(classList.size() == 4);
    }
}