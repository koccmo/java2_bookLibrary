package bookLibrary.dependency_injection;

import bookLibrary.core.dataBase.InMemoryDatabaseImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DIComponentCreatorTest {

    ApplicationContext context = new ApplicationContext();
    DIComponentCreator creator = new DIComponentCreator();

    @Test
    public void createDiComponentSuccessful() {
        List<Class> diComponents = new ArrayList<>();
        diComponents.add(InMemoryDatabaseImpl.class);
        creator.create(context, diComponents);
        assertEquals("bookLibrary.core.dataBase.InMemoryDatabaseImpl",
                context.getBean(InMemoryDatabaseImpl.class).getClass().getName());
    }

}