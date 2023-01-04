package bookLibrary.dependency_injection;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class finderTwoTest {
    @Test
    public void test() throws IOException, ClassNotFoundException {
        finderTwo finderTwo = new finderTwo();
        List<Class> classes = finderTwo.findClassesInsidePackageTwo("bookLibrary.core.dataBase");
        assertEquals(4, classes.size());
    }

}