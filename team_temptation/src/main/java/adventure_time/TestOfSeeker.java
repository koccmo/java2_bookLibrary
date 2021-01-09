package adventure_time;

import adventure_time.dependencies.ClassSearchEngine;

import java.io.IOException;
import java.util.List;

public class TestOfSeeker {

    static final void shouldFindAllFiles(String packageName) throws IOException, ClassNotFoundException  {

        ClassSearchEngine searchEngine = new ClassSearchEngine();
        List<Class> classes = searchEngine.findClassesInsidePackage(packageName);
        classes.forEach(aClass -> {
            System.out.println(aClass.getName());
        });
    }

    public static void main(String[] args) {
        try {
            shouldFindAllFiles("adventure_time");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
