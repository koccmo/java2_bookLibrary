package internet_store.dependency_injection;

import java.util.List;

public class DIApplicationContextBuilder {

    private ClassFinder classFinder = new ClassFinder();
    private DIComponentFilter diComponentFilter = new DIComponentFilter();
    private DIComponentCreator diComponentCreator = new DIComponentCreator();
    private DIDependencyResolver diDependencyResolver = new DIDependencyResolver();

    public ApplicationContext build (String packageName) {
        try {
            ApplicationContext applicationContext = new ApplicationContext();

            List<Class> classes = classFinder.findClassesInsidePackage(packageName);
            List <Class> diClasses = diComponentFilter.filter(classes);
            diComponentCreator.create(applicationContext, diClasses);
            diDependencyResolver.resolve(applicationContext, diClasses);

            return applicationContext;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
