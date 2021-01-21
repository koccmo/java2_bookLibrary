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
    Product laptopDell = new Product("Laptop", "Dell", 400);
    Product refrigerator = new Product("Refrigerator", "Electrolux", 300);
    Product tv = new Product("TV", "Radiotehnika", 3);
    Product gpsNavigator = new Product("GPS Navigator", "Car accessories", 45);
    Product laptopAcer = new Product("Laptop","Acer",300);
    Product headphones = new Product("Headphones","Beats",350);
    Product tabletPc = new Product("Tablet PC","Ipad",600);

    @Before
    public void init() {
        productDatabase.add(laptopDell);
        productDatabase.add(refrigerator);
    }
    @Test
    public void getProductListTest() {


        List<Product> listOfAllProducts = productDatabase.getProducts();

        List <Product> expectedList = new ArrayList<>();
        expectedList.add(laptopDell);
        expectedList.add(refrigerator);

        assertTrue(listOfAllProducts.equals(expectedList));

        assertTrue(listOfAllProducts.contains(laptopDell));
        assertTrue(listOfAllProducts.contains(refrigerator));
        assertEquals(listOfAllProducts.size(), 2);
    }

    @Test
    public void addProductTest() {
        assertTrue(productDatabase.getProducts().contains(laptopDell));
        assertTrue(productDatabase.getProducts().contains(refrigerator));
        assertTrue(productDatabase.getProducts().size() == 2);

    }

    @Test
    public void generateProductIdTest() {
        long firstId = laptopDell.getId();
        assertTrue(firstId == 1L);
        assertTrue(productDatabase.getProducts().get(1).getId() == 2);
    }

    @Test
    public void deleteByIdTest() {
        productDatabase.deleteById(1L);
        assertFalse(productDatabase.getProducts().contains(laptopDell));
        assertTrue(productDatabase.getProducts().contains(refrigerator));
    }

    @Test
    public void deleteByIdTestNoIdInDatabase() {
        assertTrue(productDatabase.getProducts().size() == 2);
        productDatabase.deleteById(7L);
        assertTrue(productDatabase.getProducts().size() == 2);
    }

    @Test
    public void changeProductTitleTest() {
        productDatabase.add(tv);

        productDatabase.changeTitle(1L, "Personal Computer");
        productDatabase.changeTitle(2L, "Holodiljnik");
        productDatabase.changeTitle(3L, "Televizor");

        String titleOfFirstProduct = laptopDell.getTitle();
        String titleOfSecondProduct = refrigerator.getTitle();
        String titleOfThirdProduct = tv.getTitle();

        assertTrue(titleOfFirstProduct.equals("Personal Computer"));
        assertTrue(titleOfSecondProduct.equals("Holodiljnik"));
        assertFalse(titleOfThirdProduct.equals("TV"));
    }

    @Test
    public void changeDescriptionTest() {
        productDatabase.add(tv);

        productDatabase.changeDescription(1L, "Samsung");
        productDatabase.changeDescription(2L, "LG");
        productDatabase.changeDescription(3L, "LCD Screens");

        String descriptionOfFirstProduct = laptopDell.getDescription();
        String descriptionOfSecondProduct = refrigerator.getDescription();
        String descriptionOfThirdProduct = tv.getDescription();

        assertTrue(descriptionOfFirstProduct.equals("Samsung"));
        assertFalse(descriptionOfSecondProduct.equals("Holodiljnik"));
        assertTrue(descriptionOfThirdProduct.equals("LCD Screens"));
    }

    @Test
    public void findAllProductsByTitleTest() {
        productDatabase.add(tv);
        productDatabase.add(laptopAcer);

        List<Product> resultOfFindingAllLaptops = productDatabase.findAllByTitle("Laptop");
        List<Product> resultOfFindingAllTV = productDatabase.findAllByTitle("tv");
        List<Product> resultOfFindingAllPC = productDatabase.findAllByTitle("PC");

        assertTrue(resultOfFindingAllLaptops.contains(laptopDell));
        assertTrue(resultOfFindingAllLaptops.contains(laptopAcer));
        assertEquals(resultOfFindingAllLaptops.size(), 2);

        assertFalse(resultOfFindingAllTV.contains(laptopAcer));
        assertFalse(resultOfFindingAllTV.contains(refrigerator));
        assertEquals(resultOfFindingAllTV.size(), 1);

        assertEquals(0, resultOfFindingAllPC.size());
    }

    @Test
    public void findProductByIdTest() {
        productDatabase.add(tv);
        productDatabase.add(gpsNavigator);

        Optional<Product> resultOfFindingLaptopDell = productDatabase.findById(1L);
        Optional<Product> resultOfFindingLaptopAcer = productDatabase.findById(2L);
        Optional<Product> resultOfFindingTV = productDatabase.findById(5L);
        Optional<Product> resultOfFiningGPS = productDatabase.findById(7L);

        assertTrue(resultOfFindingLaptopDell.isPresent());
        assertTrue(resultOfFindingLaptopAcer.isPresent());
        assertTrue(resultOfFindingLaptopAcer.get().equals(refrigerator));
        assertFalse(resultOfFindingTV.isPresent());
        assertFalse(resultOfFiningGPS.isPresent());
    }

    @Test
    public void findAllProductsByPriceTest() {
        productDatabase.add(laptopAcer);

        List<Product> resultOfFindingRefrigeratorAndLaptopBySamePriceRange = productDatabase.findAllByPriceRange(299,401);
        List<Product> resultOfFindingLaptopDell = productDatabase.findAllByPriceRange(301,401);

        assertTrue(resultOfFindingRefrigeratorAndLaptopBySamePriceRange.contains(refrigerator));
        assertTrue(resultOfFindingRefrigeratorAndLaptopBySamePriceRange.contains(laptopAcer));
        assertTrue(resultOfFindingLaptopDell.contains(laptopDell));

        assertFalse(resultOfFindingLaptopDell.contains(laptopAcer));
    }

    @Test
    public void deleteAllByTitleTest() {
        productDatabase.add(laptopAcer);
        productDatabase.add(laptopDell);
        productDatabase.add(refrigerator);

        boolean resultOfDeletingRefrigerator = productDatabase.deleteAllByTitle("Refrigerator");

        assertTrue(resultOfDeletingRefrigerator);
        assertFalse(productDatabase.containsProduct(refrigerator));
        assertTrue(productDatabase.containsProduct(laptopAcer));
        assertTrue(productDatabase.containsProduct(laptopDell));

        boolean resultOfDeletingLaptop = productDatabase.deleteAllByTitle("Laptop");

        assertTrue(resultOfDeletingLaptop);
        assertFalse(productDatabase.containsProduct(laptopDell));
        assertFalse(productDatabase.containsProduct(laptopAcer));
    }

    @Test
    public void deleteAllByDescriptionTest() {
        productDatabase.add(laptopAcer);
        productDatabase.add(laptopDell);
        productDatabase.add(refrigerator);
        productDatabase.add(tv);
        productDatabase.add(headphones);

       boolean resultOfDeletingAcer = productDatabase.deleteAllByDescription("Acer");
       boolean resultOfDeletingDell = productDatabase.deleteAllByDescription("Dell");

       assertTrue(resultOfDeletingAcer);
        assertTrue(resultOfDeletingDell);
        assertTrue(productDatabase.containsProduct(refrigerator));
        assertFalse(productDatabase.containsProduct(laptopAcer));
        assertFalse(productDatabase.containsProduct(laptopDell));

        productDatabase.deleteAllByDescription("Beats");

        assertFalse(productDatabase.containsProduct(headphones));
    }

    @Test
    public void deleteAllByPriceRangeTest() {
        productDatabase.add(laptopAcer);
        productDatabase.add(laptopDell);
        productDatabase.add(refrigerator);
        productDatabase.add(tv);
        productDatabase.add(headphones);

        boolean resultOfDeletingByPrice = productDatabase.deleteAllByPriceRange(4,400);

        assertTrue(resultOfDeletingByPrice);
        assertTrue(productDatabase.containsProduct(tv));
        assertFalse(productDatabase.containsProduct(tabletPc));
        assertFalse(productDatabase.containsProduct(laptopAcer));
        assertFalse(productDatabase.containsProduct(laptopDell));
        assertFalse(productDatabase.containsProduct(laptopAcer));
        assertFalse(productDatabase.containsProduct(refrigerator));
    }
}