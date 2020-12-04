package internet_store.dependency_injection;

import internet_store.core.services.customer.*;
import internet_store.core.services.product.*;
import internet_store.database.customer.CustomerDatabase;
import internet_store.database.customer.CustomerDatabaseImpl;
import internet_store.database.product.ProductDatabase;
import internet_store.database.product.ProductDatabaseImpl;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private Map<Class, Object> beans = new HashMap<>();

    public ApplicationContext() {

        beans.put(ProductDatabase.class, new ProductDatabaseImpl());

        beans.put(AddProductRequestValidator.class, new AddProductRequestValidator());
        beans.put(AddProductService.class,
                new AddProductService(getBean(ProductDatabase.class), getBean(AddProductRequestValidator.class)));

        beans.put(DeleteProductRequestValidator.class, new DeleteProductRequestValidator());
        beans.put(DeleteByIdService.class,
                new DeleteByIdService(getBean(ProductDatabase.class), getBean(DeleteProductRequestValidator.class)));

        beans.put(GetAllProductsValidator.class, new GetAllProductsValidator());
        beans.put(GetAllProductsService.class,
                new GetAllProductsService(getBean(ProductDatabase.class), getBean(GetAllProductsValidator.class)));

        beans.put(FindByIdRequestValidator.class, new FindByIdRequestValidator());
        beans.put(FindProductByIdService.class,
                new FindProductByIdService(getBean(ProductDatabase.class), getBean(FindByIdRequestValidator.class)));

        beans.put(SearchProductRequestValidator.class, new SearchProductRequestValidator());
        beans.put(SearchProductService.class,
                new SearchProductService(getBean(ProductDatabase.class), getBean(SearchProductRequestValidator.class)));

        beans.put(ChangeProductValidator.class, new ChangeProductValidator());
        beans.put(ChangeProductService.class,
                new ChangeProductService(getBean(ProductDatabase.class), getBean(ChangeProductValidator.class)));


        beans.put(CustomerDatabase.class, new CustomerDatabaseImpl());

        beans.put(AddCustomerRequestValidator.class, new AddCustomerRequestValidator());
        beans.put(AddCustomerService.class,
                new AddCustomerService(getBean(CustomerDatabase.class), getBean(AddCustomerRequestValidator.class)));

        beans.put(DeleteCustomerRequestValidator.class, new DeleteCustomerRequestValidator());
        beans.put(DeleteCustomerService.class,
                new DeleteCustomerService(getBean(CustomerDatabase.class), getBean(DeleteCustomerRequestValidator.class)));

        beans.put(GetAllCustomersValidator.class, new GetAllCustomersValidator());
        beans.put(GetAllCustomersService.class,
                new GetAllCustomersService(getBean(CustomerDatabase.class), getBean(GetAllCustomersValidator.class)));

        beans.put(FindCustomerByIdRequestValidator.class, new FindCustomerByIdRequestValidator());
        beans.put(FindCustomerByIdService.class,
                new FindCustomerByIdService(getBean(CustomerDatabase.class), getBean(FindCustomerByIdRequestValidator.class)));

        beans.put(SearchCustomerRequestValidator.class, new SearchCustomerRequestValidator());
        beans.put(SearchCustomerService.class,
                new SearchCustomerService(getBean(CustomerDatabase.class), getBean(SearchCustomerRequestValidator.class)));

    }

    public <T extends Object> T getBean(Class c) {
        return (T) beans.get(c);
    }
}
