package internet_store.application;

import internet_store.application.core.database.Database;
import internet_store.application.core.database.InMemoryDatabase;
import internet_store.application.core.services.*;
import internet_store.application.core.services.validators.*;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private Map<Class, Object> beans = new HashMap<>();

    public ApplicationContext() {
        beans.put(Database.class, new InMemoryDatabase());

        beans.put(DeleteByProductNameValidator.class, new DeleteByProductNameValidator());
        beans.put(DeleteByProductValidator.class, new DeleteByProductValidator());
        beans.put(DeleteByProductIdValidator.class, new DeleteByProductIdValidator());
        beans.put(FindByIdValidator.class, new FindByIdValidator());
        beans.put(AddProductValidator.class, new AddProductValidator());
        beans.put(ChangeProductNameValidator.class, new ChangeProductNameValidator());
        beans.put(FindProductsRequestValidator.class, new FindProductsRequestValidator());

        beans.put(AddProductService.class, new AddProductService(getBean(Database.class), getBean(AddProductValidator.class)));
        beans.put(FindByIdService.class, new FindByIdService(getBean(Database.class), getBean(FindByIdValidator.class)));
        beans.put(GetProductListService.class, new GetProductListService(getBean(Database.class)));
//        beans.put(DeleteProductService.class, new DeleteProductService(getBean(Database.class), (DeleteByProductNameValidator) getBean(DeleteByProductNameValidator.class)));
//        beans.put(DeleteProductService.class, new DeleteProductService(getBean(Database.class), (DeleteByProductValidator) getBean(DeleteByProductValidator.class)));
        beans.put(DeleteByProductIdService.class, new DeleteByProductIdService(getBean(Database.class), getBean(DeleteByProductIdValidator.class)));
        beans.put(ChangeProductNameService.class, new ChangeProductNameService(getBean(Database.class), getBean(ChangeProductNameValidator.class)));
        beans.put(PagingProductsService.class, new PagingProductsService());
        beans.put(OrderingProductsService.class, new OrderingProductsService());
//        beans.put(FindProductsService.class, new FindProductsService(getBean(Database.class),
//                getBean(FindProductsRequestValidator.class)),
//                OrderingProductsService.class,
//                PagingProductsService.class)));


    }

    public <T extends Object> T getBean(Class c) {
        return (T) beans.get(c);
    }

}
