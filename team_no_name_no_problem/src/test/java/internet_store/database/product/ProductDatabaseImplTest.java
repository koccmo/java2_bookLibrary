package internet_store.database.product;

import internet_store.domain.Product;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class ProductDatabaseImplTest {

    @Test
    public void getProductListTest() {
        ProductDatabaseImpl productDatabase = new ProductDatabaseImpl();

        Product firstProduct = new Product("Laptop", "Dell", 400);
        Product secondProduct = new Product("Refrigerator", "Electrolux", 300);
        productDatabase.add(firstProduct);
        productDatabase.add(secondProduct);

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
        ProductDatabaseImpl productDatabase = new ProductDatabaseImpl();

        Product firstProduct = new Product("Laptop", "Dell", 400);
        Product secondProduct = new Product("Refrigerator", "Electrolux", 300);
        productDatabase.add(firstProduct);
        productDatabase.add(secondProduct);
        assertTrue(productDatabase.getProducts().contains(firstProduct));
        assertTrue(productDatabase.getProducts().contains(secondProduct));
        assertTrue(productDatabase.getProducts().size() == 2);

    }

    @Test
    public void generateProductIdTest() {
        ProductDatabaseImpl productDatabase = new ProductDatabaseImpl();

        Product firstProduct = new Product("Laptop", "Dell", 400);
        Product secondProduct = new Product("Refrigerator", "Electrolux", 300);
        productDatabase.add(firstProduct);
        productDatabase.add(secondProduct);
        long firstId = firstProduct.getId();
        long secondId = secondProduct.getId();
        assertTrue(firstId == 1L);
        assertTrue(productDatabase.getProducts().get(1).getId() == 2);
    }

    @Test
    public void deleteByIdTest() {
        ProductDatabaseImpl productDatabase = new ProductDatabaseImpl();

        Product firstProduct = new Product("Laptop", "Dell", 400);
        Product secondProduct = new Product("Refrigerator", "Electrolux", 300);
        productDatabase.add(firstProduct);
        productDatabase.add(secondProduct);
        productDatabase.deleteById(1L);
        assertFalse(productDatabase.getProducts().contains(firstProduct));
        assertTrue(productDatabase.getProducts().contains(secondProduct));
    }

    @Test
    public void deleteByIdTestNoIdInDatabase() {
        ProductDatabaseImpl productDatabase = new ProductDatabaseImpl();

        Product firstProduct = new Product("Laptop", "Dell", 400);
        Product secondProduct = new Product("Refrigerator", "Electrolux", 300);
        productDatabase.add(firstProduct);
        productDatabase.add(secondProduct);
        assertTrue(productDatabase.getProducts().size() == 2);
        productDatabase.deleteById(7L);
        assertTrue(productDatabase.getProducts().size() == 2);
    }

    @Test
    public void changeProductTitleTest() {
        ProductDatabaseImpl productDatabase = new ProductDatabaseImpl();

        Product firstProduct = new Product("Laptop", "Dell", 400);
        Product secondProduct = new Product("Refrigerator", "Electrolux", 300);
        Product thirdProduct = new Product("TV", "Radiotehnika", 3);
        productDatabase.add(firstProduct);
        productDatabase.add(secondProduct);
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
        ProductDatabaseImpl productDatabase = new ProductDatabaseImpl();

        Product firstProduct = new Product("Laptop", "Dell", 400);
        Product secondProduct = new Product("Refrigerator", "Electrolux", 300);
        Product thirdProduct = new Product("TV", "Radiotehnika", 3);
        productDatabase.add(firstProduct);
        productDatabase.add(secondProduct);
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
    public void findAnyProductByTitleTest() {
        ProductDatabaseImpl productDatabase = new ProductDatabaseImpl();

        Product firstProduct = new Product("Laptop", "Dell", 400);
        Product secondProduct = new Product("Refrigerator", "Electrolux", 300);
        Product thirdProduct = new Product("TV", "Radiotehnika", 3);
        productDatabase.add(firstProduct);
        productDatabase.add(secondProduct);
        productDatabase.add(thirdProduct);

        Optional<Product> resultOfFindingLaptop = productDatabase.findAnyByTitle("Laptop");
        Optional<Product> resultOfFindingRefrigerator = productDatabase.findAnyByTitle("REFRIGERATOR");
        Optional<Product> resultOfFindingTV = productDatabase.findAnyByTitle("TV");
        Optional<Product> resultOfFindingMicrophone = productDatabase.findAnyByTitle("Microphone");

        assertTrue(resultOfFindingLaptop.isPresent());
        assertTrue(resultOfFindingRefrigerator.isPresent());
        assertTrue(resultOfFindingTV.isPresent());
        assertFalse(resultOfFindingMicrophone.isPresent());

    }

    @Test
    public void findAllProductsByTitleTest() {
        ProductDatabaseImpl productDatabase = new ProductDatabaseImpl();

        Product firstProduct = new Product("Laptop", "Dell", 400);
        Product secondProduct = new Product("Laptop", "Acer", 300);
        Product thirdProduct = new Product("TV", "Radiotehnika", 3);
        productDatabase.add(firstProduct);
        productDatabase.add(secondProduct);
        productDatabase.add(thirdProduct);

        List<Product> resultOfFindingAllLaptops = productDatabase.findAllByTitle("Laptop");
        List<Product> resultOfFindingAllTV = productDatabase.findAllByTitle("tv");
        List<Product> resultOfFindingAllPC = productDatabase.findAllByTitle("PC");

        assertTrue(resultOfFindingAllLaptops.contains(firstProduct));
        assertTrue(resultOfFindingAllLaptops.contains(secondProduct));
        assertEquals(resultOfFindingAllLaptops.size(), 2);

        assertTrue(resultOfFindingAllTV.contains(thirdProduct));
        assertFalse(resultOfFindingAllTV.contains(secondProduct));
        assertEquals(resultOfFindingAllTV.size(), 1);

        assertEquals(0, resultOfFindingAllPC.size());
    }

    @Test
    public void findProductByIdTest() {
        ProductDatabaseImpl productDatabase = new ProductDatabaseImpl();

        Product firstProduct = new Product("Laptop", "Dell", 400);
        Product secondProduct = new Product("Laptop", "Acer", 300);
        Product thirdProduct = new Product("TV", "Radiotehnika", 3);
        Product fourthProduct = new Product("GPS Navigator", "Car accessories", 45);

        productDatabase.add(firstProduct);
        productDatabase.add(secondProduct);
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