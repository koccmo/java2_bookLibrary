package adventure_time.dependencies;

import java.util.List;

public class DIApplicationContextBuilder {

    private ClassSearchEngine searchEngine = new ClassSearchEngine();
    private DIComponentFilter filter = new DIComponentFilter();
    private DIComponentCreator creator = new DIComponentCreator();
    private DIDependencyResolver resolver = new DIDependencyResolver();

    public EventApplicationContext build(String packageName) {
        try {
            List<Class> allPackageClasses = searchEngine.findClassesInsidePackage(packageName);
            List<Class> diComponents = filter.filter(allPackageClasses);

            EventApplicationContext applicationContext = new EventApplicationContext();
            creator.create(applicationContext, diComponents);
            resolver.resolve(applicationContext, diComponents);

            return applicationContext;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
