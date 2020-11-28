package internet_store.application;

import internet_store.application.core.database.Database;
import internet_store.application.core.database.InMemoryDatabase;
import internet_store.application.core.services.*;
import internet_store.application.core.services.validators.*;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private final Map<Class, Object> beans = new HashMap<>();

    public ApplicationContext() {
        beans.put(Database.class, new InMemoryDatabase());

        beans.put(AddProductValidator.class, new AddProductValidator());
        beans.put(DeleteByProductIdValidator.class, new DeleteByProductIdValidator());
        beans.put(DeleteByProductValidator.class, new DeleteByProductValidator());
        beans.put(DeleteByProductNameValidator.class, new DeleteByProductNameValidator());
        beans.put(FindByIdValidator.class, new FindByIdValidator());
        beans.put(FindProductsRequestValidator.class, new FindProductsRequestValidator());
        beans.put(ChangeProductNameValidator.class, new ChangeProductNameValidator());

        beans.put(AddProductService.class, new AddProductService(getBean(Database.class), getBean(AddProductValidator.class)));
        beans.put(DeleteByProductIdService.class, new DeleteByProductIdService(getBean(Database.class), getBean(DeleteByProductIdValidator.class)));
        beans.put(DeleteProductByProductService.class, new DeleteProductByProductService(getBean(Database.class), getBean(DeleteByProductValidator.class)));
        beans.put(DeleteProductByNameService.class, new DeleteProductByNameService(getBean(Database.class), getBean(DeleteByProductNameValidator.class)));
        beans.put(FindByIdService.class, new FindByIdService(getBean(Database.class), getBean(FindByIdValidator.class)));
        beans.put(FindProductsService.class, new FindProductsService(getBean(Database.class), getBean(FindProductsRequestValidator.class)));
        beans.put(ChangeProductNameService.class, new ChangeProductNameService(getBean(Database.class), getBean(ChangeProductNameValidator.class)));
        beans.put(GetProductListService.class, new GetProductListService(getBean(Database.class)));
    }

    public <T extends Object> T getBean(Class c) {
        return (T) beans.get(c);
    }

}
