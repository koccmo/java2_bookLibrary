package dependency;

import adventure_time.dependencies.ClassSearchEngine;

import java.io.IOException;
import java.util.List;

class ClassSeekerNewTest {

    @org.junit.jupiter.api.Test
    void shouldFindAllFiles() throws IOException, ClassNotFoundException  {

        ClassSearchEngine searchEngine = new ClassSearchEngine();
        List<Class> classes = searchEngine.findClassesInsidePackage("core");
        classes.forEach(aClass -> {
            System.out.println(aClass.getName());
        });
    }
}