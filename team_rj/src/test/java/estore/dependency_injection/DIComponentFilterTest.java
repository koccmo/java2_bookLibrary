package estore.dependency_injection;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DIComponentFilterTest {

    DIComponentFilter DIfilter = new DIComponentFilter();
    ClassFinder classFinder = new ClassFinder();

    @Test
    public void ShouldReturnListWithAnnotatedFiles() throws IOException, ClassNotFoundException {
        List<Class> classList = classFinder.findClassesInsidePackage("estore.core.domain");
        List<Class> annotatedList = DIfilter.filter(classList);
        assertEquals(classList.size(), 2);
        assertEquals(annotatedList.size(), 0);
        annotatedList.forEach(aClass ->
            System.out.println(aClass.getName())
        );
    }
}
