package internet_store_tests.database.product;

import internet_store.core.domain.Product;
import internet_store.database.product.ProductDatabaseImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class ProductDatabaseImplTest {

    ProductDatabaseImpl productDatabase = new ProductDatabaseImpl();
    Product firstProduct = new Product("Laptop", "Dell", 400);
    Product secondProduct = new Product("Refrigerator", "Electrolux", 300);
    Product thirdProduct = new Product("TV", "Radiotehnika", 3);
    Product fourthProduct = new Product("GPS Navigator", "Car accessories", 45);
    Product fifthProduct = new Product("Laptop","Acer",300);

    @Before
    public void init() {
        productDatabase.add(firstProduct);
        productDatabase.add(secondProduct);
    }
    @Test
    public void getProductListTest() {


        List<Product> listOfAllProducts = productDatabase.getProducts();

        List <Product> expectedList = new ArrayList<>();
        expectedList.add(firstProduct);
        expectedList.add(secondProduct);

        assertTrue(listOfAllProducts.equals(expectedList));

        assertTrue(listOfAllProducts.contains(firstProduct));
        assertTrue(listOfAllProducts.contains(secondProduct));
        assertEquals(listOfAllProducts.size(), 2);
    }

    @Test
    public void addProductTest() {
        assertTrue(productDatabase.getProducts().contains(firstProduct));
        assertTrue(productDatabase.getProducts().contains(secondProduct));
        assertTrue(productDatabase.getProducts().size() == 2);

    }

    @Test
    public void generateProductIdTest() {
        long firstId = firstProduct.getId();
        assertTrue(firstId == 1L);
        assertTrue(productDatabase.getProducts().get(1).getId() == 2);
    }

    @Test
    public void deleteByIdTest() {
        productDatabase.deleteById(1L);
        assertFalse(productDatabase.getProducts().contains(firstProduct));
        assertTrue(productDatabase.getProducts().contains(secondProduct));
    }

    @Test
    public void deleteByIdTestNoIdInDatabase() {
        assertTrue(productDatabase.getProducts().size() == 2);
        productDatabase.deleteById(7L);
        assertTrue(productDatabase.getProducts().size() == 2);
    }

    @Test
    public void changeProductTitleTest() {
        productDatabase.add(thirdProduct);

        productDatabase.changeTitle(1L, "Personal Computer");
        productDatabase.changeTitle(2L, "Holodiljnik");
        productDatabase.changeTitle(3L, "Televizor");

        String titleOfFirstProduct = firstProduct.getTitle();
        String titleOfSecondProduct = secondProduct.getTitle();
        String titleOfThirdProduct = thirdProduct.getTitle();

        assertTrue(titleOfFirstProduct.equals("Personal Computer"));
        assertTrue(titleOfSecondProduct.equals("Holodiljnik"));
        assertFalse(titleOfThirdProduct.equals("TV"));
    }

    @Test
    public void changeDescriptionTest() {
        productDatabase.add(thirdProduct);

        productDatabase.changeDescription(1L, "Samsung");
        productDatabase.changeDescription(2L, "LG");
        productDatabase.changeDescription(3L, "LCD Screens");

        String descriptionOfFirstProduct = firstProduct.getDescription();
        String descriptionOfSecondProduct = secondProduct.getDescription();
        String descriptionOfThirdProduct = thirdProduct.getDescription();

        assertTrue(descriptionOfFirstProduct.equals("Samsung"));
        assertFalse(descriptionOfSecondProduct.equals("Holodiljnik"));
        assertTrue(descriptionOfThirdProduct.equals("LCD Screens"));
    }

    @Test
    public void findAllProductsByTitleTest() {
        productDatabase.add(thirdProduct);
        productDatabase.add(fifthProduct);

        List<Product> resultOfFindingAllLaptops = productDatabase.findAllByTitle("Laptop");
        List<Product> resultOfFindingAllTV = productDatabase.findAllByTitle("tv");
        List<Product> resultOfFindingAllPC = productDatabase.findAllByTitle("PC");

        assertTrue(resultOfFindingAllLaptops.contains(firstProduct));
        assertTrue(resultOfFindingAllLaptops.contains(fifthProduct));
        assertEquals(resultOfFindingAllLaptops.size(), 2);

        assertFalse(resultOfFindingAllTV.contains(fifthProduct));
        assertFalse(resultOfFindingAllTV.contains(secondProduct));
        assertEquals(resultOfFindingAllTV.size(), 1);

        assertEquals(0, resultOfFindingAllPC.size());
    }

    @Test
    public void findProductByIdTest() {
        productDatabase.add(thirdProduct);
        productDatabase.add(fourthProduct);

        Optional<Product> resultOfFindingLaptopDell = productDatabase.findById(1L);
        Optional<Product> resultOfFindingLaptopAcer = productDatabase.findById(2L);
        Optional<Product> resultOfFindingTV = productDatabase.findById(5L);
        Optional<Product> resultOfFiningGPS = productDatabase.findById(7L);

        assertTrue(resultOfFindingLaptopDell.isPresent());
        assertTrue(resultOfFindingLaptopAcer.isPresent());
        assertTrue(resultOfFindingLaptopAcer.get().equals(secondProduct));
        assertFalse(resultOfFindingTV.isPresent());
        assertFalse(resultOfFiningGPS.isPresent());
    }

}