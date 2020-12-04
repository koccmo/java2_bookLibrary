package dental_clinic.dependency_injection;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class ClassFinderTest {

    ClassFinder classFinder = new ClassFinder();

    @Test
    public void testReceivedFiles() throws IOException, ClassNotFoundException{

        List<Class> classList = classFinder.findClassesInsidePackage("dental_clinic.console_ui");

        assertTrue(classList.size() == 14);

        assertTrue (classList.get(0).getName().equals("dental_clinic.console_ui.AddPatientUIAction"));
    }

    @Test
    public void testReceivedFiles2() throws IOException, ClassNotFoundException{

        List<Class> classList = classFinder.findClassesInsidePackage("dental_clinic.database");

        assertTrue(classList.size() == 3);

        assertTrue (classList.get(2).getName().equals("dental_clinic.database.PatientDatabaseImpl"));
    }
}