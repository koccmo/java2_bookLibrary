package lesson_6;

import java.util.List;

public class DIApplicationContextBuilder {
    private final ClassFinder classFinder = new ClassFinder();
    private final DIComponentFilter componentFilter = new DIComponentFilter();
    private final DIComponentCreator componentsCreator = new DIComponentCreator();
    private final DIDependencyResolver dependencyResolver = new DIDependencyResolver();

    public ApplicationContext build(Class<?> startClass) {
        try {
            List<Class<?>> allPackageClasses = classFinder.findAllClassesInPackage(startClass);
            List<Class<?>> diComponents = componentFilter.filter(allPackageClasses);

            ApplicationContext applicationContext = new ApplicationContext();

            componentsCreator.create(applicationContext, diComponents);
            dependencyResolver.resolve(applicationContext, diComponents);

            return applicationContext;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}