package internet_store.lesson_6.dependency_injection;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DIComponentCreatorTest {

    private internet_store.lesson_6.dependency_injection.DIComponentCreator creator;
    private ApplicationContext applicationContext;
    private List<Class> diComponents;

    @Before
    public void setUp() {
        creator = new internet_store.lesson_6.dependency_injection.DIComponentCreator();
        applicationContext = new ApplicationContext();
        diComponents = new ArrayList<>();
        diComponents.add(Class1.class);
        diComponents.add(Class2.class);
        diComponents.add(Class3.class);
    }

    @Test
    public void create() {
        creator.create(applicationContext, diComponents);
        assertEquals(new Class1(), applicationContext.getBean(Class1.class));
        assertEquals(new Class2(), applicationContext.getBean(Class2.class));
        assertEquals(new Class3(), applicationContext.getBean(Class3.class));
    }

    @DIComponent
    public static class Class1 {
        @Override
        public boolean equals(Object obj) {
            return obj instanceof Class1;
        }
    }

    @DIComponent
    public static class Class2 {
        @Override
        public boolean equals(Object obj) {
            return obj instanceof Class2;
        }
    }

    @DIComponent
    public static class Class3 {
        @Override
        public boolean equals(Object obj) {
            return obj instanceof Class3;
        }
    }
}