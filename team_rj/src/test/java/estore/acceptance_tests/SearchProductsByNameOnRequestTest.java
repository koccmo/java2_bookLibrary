package estore.acceptance_tests;

import estore.config.ProductConfiguration;
import estore.core.requests.*;
import estore.core.responses.SearchProductByNameResponse;
import estore.core.service.*;
import estore.database.ProductDB;
import estore.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SearchProductsByNameOnRequestTest {

    private static ApplicationContext applicationContext;

    @Before
    public void setup() {
        applicationContext = new AnnotationConfigApplicationContext(ProductConfiguration.class);
        getDatabaseCleaner().clean();
    }

    private DatabaseCleaner getDatabaseCleaner() {
        return applicationContext.getBean(DatabaseCleaner.class);
    }

    @Test
    public void shouldSearchProductByName() {
        AddNewProductCategoryRequest addNewProductCategoryRequest = new AddNewProductCategoryRequest("Category");
        AddNewProductCategoryService().execute(addNewProductCategoryRequest);

        AddNewProductRequest addProductRequest1 = new AddNewProductRequest("ProductA", "Description ProductA1", "1");
        AddNewProductRequest addProductRequest2 = new AddNewProductRequest("ProductB", "Description ProductB", "1");
        AddNewProductRequest addProductRequest3 = new AddNewProductRequest("ProductA", "Description ProductA2", "1");

        addNewProductService().execute(addProductRequest1);
        addNewProductService().execute(addProductRequest2);
        addNewProductService().execute(addProductRequest3);

        SearchProductByNameRequest request = new SearchProductByNameRequest("ProductA");
        SearchProductByNameResponse response = searchProductByNameService().execute(request);

        assertEquals(response.getProducts().size(), 2);
        assertEquals(response.getProducts().get(0).getName(), "ProductA");
        assertEquals(response.getProducts().get(0).getDescription(), "Description ProductA1");
        assertEquals(response.getProducts().get(1).getDescription(), "Description ProductA2");
    }

    @Test
    public void shouldSearchProductByNameDescending() {
        AddNewProductCategoryRequest addNewProductCategoryRequest = new AddNewProductCategoryRequest("Category");
        AddNewProductCategoryService().execute(addNewProductCategoryRequest);

        AddNewProductRequest addProductRequest1 = new AddNewProductRequest("ProductA", "Description ProductA1", "1");
        AddNewProductRequest addProductRequest2 = new AddNewProductRequest("ProductB", "Description ProductB", "1");
        AddNewProductRequest addProductRequest3 = new AddNewProductRequest("ProductA", "Description ProductA2", "1");

        addNewProductService().execute(addProductRequest1);
        addNewProductService().execute(addProductRequest2);
        addNewProductService().execute(addProductRequest3);

//        changeProductPrice(1L, "1.0");
//        jdbcTemplate.update("UPDATE eStore_test.products " +
//                "SET products.price = ? " +
//                "WHERE products.id = ?");
//        jdbcTemplate.update("UPDATE eStore_test.products " +
//                "SET products.price = 2 " +
//                "WHERE products.id = 2");
//        jdbcTemplate.update("UPDATE eStore_test.products " +
//                "SET products.price = 1 " +
//                "WHERE products.id = 1");

//        getProductDb().getDatabase().get(2).setPrice(3);
//        getProductDb().getDatabase().get(1).setPrice(2);
//        getProductDb().getDatabase().get(0).setPrice(1);

        Ordering ordering = new Ordering("price", "desc");
        SearchProductByNameRequest request = new SearchProductByNameRequest("ProductA", ordering);
        SearchProductByNameResponse response = searchProductByNameService().execute(request);

        assertEquals(response.getProducts().size(), 2);
//        assertEquals(response.getProducts().get(0).getName(), "ProductA");
//        assertEquals(response.getProducts().get(0).getPrice(), 3, 0.01);
//        assertEquals(response.getProducts().get(1).getPrice(), 1, 0.01);
    }

    @Test
    public void shouldSearchProductByNameAscendingAndPaging() {
//        AddNewProductRequest addProductRequest1 = new AddNewProductRequest("ProductA", "Description ProductA1", "Fruits");
//        AddNewProductRequest addProductRequest2 = new AddNewProductRequest("ProductB", "Description ProductB", "Fruits");
//        AddNewProductRequest addProductRequest3 = new AddNewProductRequest("ProductA", "Description ProductA2", "Fruits");
//
//        addNewProductService().execute(addProductRequest1);
//        addNewProductService().execute(addProductRequest2);
//        addNewProductService().execute(addProductRequest3);
//
//        int dbSize = getProductDb().getDatabaseSize();
//        getProductDb().getDatabase().get(dbSize-1).setPrice(1);
//        getProductDb().getDatabase().get(dbSize-2).setPrice(2);
//        getProductDb().getDatabase().get(dbSize-3).setPrice(3);
//
//        Ordering ordering = new Ordering("price", "asc");
//        Paging paging = new Paging("1", "1");
//        SearchProductByNameRequest request = new SearchProductByNameRequest("ProductA", ordering, paging);
//        SearchProductByNameResponse response = searchProductByNameService().execute(request);
//
//        assertEquals(response.getProducts().size(), 1);
//        assertEquals(response.getProducts().get(0).getName(), "ProductA");
//        assertEquals(response.getProducts().get(0).getPrice(), 1, 0.01);
    }

    private AddNewProductService addNewProductService() {
        return applicationContext.getBean(AddNewProductService.class);
    }

    private SearchProductByNameService searchProductByNameService() {
        return applicationContext.getBean(SearchProductByNameService.class);
    }

    private ProductDB getProductDb() {
        return applicationContext.getBean(ProductDB.class);
    }

    private AddNewProductCategoryService AddNewProductCategoryService() {
        return applicationContext.getBean(AddNewProductCategoryService.class);
    }

    private GetAllProductsService getAllProductsService() {
        return applicationContext.getBean(GetAllProductsService.class);
    }

//    public void changeProductPrice(Long id, String price) {
//        String query = "UPDATE eStore_test.products " +
//                "SET products.price = ? " +
//                "WHERE products.id = ?";
//        jdbcTemplate.update(connection -> {
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, price);
//            preparedStatement.setLong(2, id);
//            return preparedStatement;
//        });
//    }
}
