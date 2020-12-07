package internet_store_tests.dependency_injection;

import internet_store.dependency_injection.ClassFinder;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ClassFinderTest {

    ClassFinder classFinder = new ClassFinder();

    @Test
    public void testReceivedClasses() throws IOException, ClassNotFoundException{

        List<Class> listOfClasses = classFinder.findClassesInsidePackage("internet_store.console_ui");

        assertTrue(listOfClasses.size() == 20);

        assertTrue(listOfClasses.get(0).getName().equals("internet_store.console_ui.customer.AddCustomerUIAction"));
    }

    @Test
    public void testReceivedClasses2() throws IOException, ClassNotFoundException{

        List<Class> listOfClasses2 = classFinder.findClassesInsidePackage("internet_store.core");

        assertTrue(listOfClasses2.size() == 59);

        assertTrue(listOfClasses2.get(2).getName().equals("internet_store.core.domain.Product"));
    }

    @Test
    public void testReceivedClasses3() throws IOException, ClassNotFoundException{

        List<Class> listOfClasses3 = classFinder.findClassesInsidePackage("internet_store.database");

        assertTrue(listOfClasses3.size() == 6);

        assertTrue(listOfClasses3.get(3).getName().equals("internet_store.database.order.OrderDatabaseImpl"));
    }
}
