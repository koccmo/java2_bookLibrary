package internet_store.application.dependency_injection;

import internet_store.application.dependency_injection.test_classes.TestClass1;
import internet_store.application.dependency_injection.test_classes.TestClass2;
import internet_store.application.dependency_injection.test_classes.TestClass3;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DIComponentFilterTest {

    private DIComponentFilter filter;

    @Before
    public void setUp() throws Exception {
        filter = new DIComponentFilter();
    }

    @Test
    public void filter() {
        List<Class> classList = new ArrayList<>();
        classList.add(TestClass1.class);
        classList.add(TestClass2.class);
        classList.add(TestClass3.class);
        List<Class> filtered = this.filter.filter(classList);
        assertEquals(filtered.size(), 2);
    }

}