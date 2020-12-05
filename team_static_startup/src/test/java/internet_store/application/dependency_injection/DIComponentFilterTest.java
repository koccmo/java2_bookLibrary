package internet_store.application.dependency_injection;

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


    @DIComponent
    private class TestClass1 {
    }

    @DIComponent
    private class TestClass2 {
    }

    private class TestClass3 {
    }

}