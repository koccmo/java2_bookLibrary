package team_VK.application.dependenci_injection;

import team_VK.application.ApplicationContext;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class DIApplicationContextBuilder {

    private ClassFinder classFinder = new ClassFinder();
    private DIComponentFilter componentFilter = new DIComponentFilter();
    private DIComponentCreator componentCreator = new DIComponentCreator();
    private DIDependencyResolver dependencyResolver = new DIDependencyResolver();


    public ApplicationContext build(String packageName) throws IOException, ClassNotFoundException {
        ApplicationContext applicationContext = new ApplicationContext();
        List<Class> classes = classFinder.getClasses(packageName);
        List<Class> diComponents = componentFilter.filter(classes);
        componentCreator.create(applicationContext, diComponents);
        dependencyResolver.resolve(applicationContext,diComponents);

        return applicationContext;
    }
}
