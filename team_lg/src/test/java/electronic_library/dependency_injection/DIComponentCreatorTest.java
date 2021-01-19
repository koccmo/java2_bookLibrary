package electronic_library.dependency_injection;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DIComponentCreatorTest {

    private DIComponentCreator creator;
    private ApplicationContext applicationContext;
    private List<Class> diComponents;

    @Before
    public void setUp() {
        creator = new DIComponentCreator();
        applicationContext = new ApplicationContext();
        diComponents = new ArrayList<>();
        diComponents.add(ObjectOne.class);
        diComponents.add(ObjectTwo.class);
        diComponents.add(ObjectThree.class);
        diComponents.add(ObjectFour.class);
    }

    @Test
    public void create() {
        creator.create(applicationContext, diComponents);
        assertEquals(new ObjectOne(), applicationContext.getBean(ObjectOne.class));
        assertEquals(new ObjectTwo(), applicationContext.getBean(ObjectTwo.class));
        assertEquals(new ObjectThree(), applicationContext.getBean(ObjectThree.class));
        assertEquals(new ObjectFour(), applicationContext.getBean(ObjectFour.class));
    }

    public static class ObjectOne {
        @Override
        public boolean equals(Object obj) { return obj instanceof ObjectOne; }
    }

    public static class ObjectTwo {
        @Override
        public boolean equals(Object obj) {
            return obj instanceof ObjectTwo;
        }
    }

    public static class ObjectThree {
        @Override
        public boolean equals(Object obj) {
            return obj instanceof ObjectThree;
        }
    }

    public static class ObjectFour {
        @Override
        public boolean equals(Object obj) {
            return obj instanceof ObjectFour;
        }
    }
}