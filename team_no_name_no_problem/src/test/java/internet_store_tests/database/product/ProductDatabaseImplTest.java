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
    Product junkLaptop = new Product("Laptop","junks",3);
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

        List<Product> resultOfFindingAllLaptops = productDatabase.searchAllByTitle("Laptop");
        List<Product> resultOfFindingAllTV = productDatabase.searchAllByTitle("tv");
        List<Product> resultOfFindingAllPC = productDatabase.searchAllByTitle("PC");

        assertTrue(resultOfFindingAllLaptops.contains(laptopDell));
        assertTrue(resultOfFindingAllLaptops.contains(laptopAcer));
        assertEquals(resultOfFindingAllLaptops.size(), 2);

        assertFalse(resultOfFindingAllTV.contains(laptopAcer));
        assertFalse(resultOfFindingAllTV.contains(refrigerator));
        assertEquals(resultOfFindingAllTV.size(), 1);

        assertEquals(0, resultOfFindingAllPC.size());
    }

    @Test
    public void findAllProductsByDescriptionTest() {
        productDatabase.add(tv);
        productDatabase.add(laptopAcer);

        List<Product> resultOfFindingAllLaptopAcer = productDatabase.searchAllByDescription("Acer");
        List<Product> resultOfFindingAllTV = productDatabase.searchAllByTitle("tv");
        List<Product> resultOfFindingAllPC = productDatabase.searchAllByTitle("PC");

        assertTrue(resultOfFindingAllLaptopAcer.contains(laptopAcer));
        assertEquals(resultOfFindingAllLaptopAcer.size(), 1);

        assertFalse(resultOfFindingAllTV.contains(laptopAcer));
        assertFalse(resultOfFindingAllTV.contains(refrigerator));
        assertEquals(resultOfFindingAllTV.size(), 1);

        assertEquals(0, resultOfFindingAllPC.size());
    }

    @Test
    public void findProductByIdTest() {
        productDatabase.add(tv);
        productDatabase.add(gpsNavigator);

        Optional<Product> resultOfFindingLaptopDell = productDatabase.searchById(1L);
        Optional<Product> resultOfFindingLaptopAcer = productDatabase.searchById(2L);
        Optional<Product> resultOfFindingTV = productDatabase.searchById(5L);
        Optional<Product> resultOfFiningGPS = productDatabase.searchById(7L);

        assertTrue(resultOfFindingLaptopDell.isPresent());
        assertTrue(resultOfFindingLaptopAcer.isPresent());
        assertTrue(resultOfFindingLaptopAcer.get().equals(refrigerator));
        assertFalse(resultOfFindingTV.isPresent());
        assertFalse(resultOfFiningGPS.isPresent());
    }

    @Test
    public void findAllProductsByPriceTest() {
        productDatabase.add(laptopAcer);

        List<Product> resultOfFindingRefrigeratorAndLaptopBySamePriceRange = productDatabase.searchAllByPriceRange(299,401);
        List<Product> resultOfFindingLaptopDell = productDatabase.searchAllByPriceRange(301,401);

        assertTrue(resultOfFindingRefrigeratorAndLaptopBySamePriceRange.contains(refrigerator));
        assertTrue(resultOfFindingRefrigeratorAndLaptopBySamePriceRange.contains(laptopAcer));
        assertTrue(resultOfFindingLaptopDell.contains(laptopDell));

        assertFalse(resultOfFindingLaptopDell.contains(laptopAcer));
    }

    @Test
    public void deleteAllProductsByTitleTest() {
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
    public void deleteAllProductsByDescriptionTest() {
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
    public void deleteAllProductsByPriceRangeTest() {
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

    @Test
    public void findAllProductsByTitleAndDescriptionTest() {
        productDatabase.add(laptopAcer);
        productDatabase.add(laptopDell);
        productDatabase.add(refrigerator);
        productDatabase.add(tv);
        productDatabase.add(headphones);

        List<Product> resultOfFindingRefrigerator = productDatabase.searchAllByTitleAndDescription("","Electrolux");
        List<Product> resultOfFindingAllLaptops = productDatabase.searchAllByTitleAndDescription("Laptop","");

        assertTrue(resultOfFindingRefrigerator.contains(refrigerator));
        assertTrue(resultOfFindingAllLaptops.contains(laptopAcer));
        assertTrue(resultOfFindingAllLaptops.contains(laptopDell));
        assertFalse(resultOfFindingRefrigerator.contains(tv));
        assertFalse(resultOfFindingRefrigerator.contains(headphones));
        assertFalse(resultOfFindingRefrigerator.contains(laptopDell));

    }

    @Test
    public void findAllProductsByTitleAndPriceRangeTest() {
        productDatabase.add(laptopAcer);
        productDatabase.add(laptopDell);
        productDatabase.add(junkLaptop);
        productDatabase.add(tv);
        productDatabase.add(headphones);

        List<Product> resultOfFindingJunkLaptop = productDatabase.searchAllByTitleAndPriceRange("Laptop",1,5);

        assertTrue(resultOfFindingJunkLaptop.contains(junkLaptop));
        assertFalse(resultOfFindingJunkLaptop.contains(tv));
        assertFalse(resultOfFindingJunkLaptop.contains(tv));
        assertFalse(resultOfFindingJunkLaptop.contains(headphones));
        assertFalse(resultOfFindingJunkLaptop.contains(laptopDell));

    }

    @Test
    public void findAllProductsByDescriptionAndPriceRangeTest() {
        productDatabase.add(laptopAcer);
        productDatabase.add(laptopDell);
        productDatabase.add(junkLaptop);
        productDatabase.add(tv);
        productDatabase.add(headphones);

        List<Product> resultOfFindingJunkLaptop = productDatabase.searchAllByDescriptionAndPriceRange("Junk",1,5);

        assertTrue(resultOfFindingJunkLaptop.contains(junkLaptop));
        assertFalse(resultOfFindingJunkLaptop.contains(tv));
        assertFalse(resultOfFindingJunkLaptop.contains(tv));
        assertFalse(resultOfFindingJunkLaptop.contains(headphones));
        assertFalse(resultOfFindingJunkLaptop.contains(laptopDell));

    }

    @Test
    public void findAllProductsByTitleAndDescriptionAndPriceRangeTest() {
        productDatabase.add(laptopAcer);
        productDatabase.add(laptopDell);
        productDatabase.add(junkLaptop);
        productDatabase.add(tv);
        productDatabase.add(headphones);

        List<Product> resultOfFindingJunkLaptop = productDatabase.searchAllByTitleAndDescriptionAndPriceRange("Laptop","Junks",1,5);

        assertTrue(resultOfFindingJunkLaptop.contains(junkLaptop));
        assertFalse(resultOfFindingJunkLaptop.contains(tv));
        assertFalse(resultOfFindingJunkLaptop.contains(tv));
        assertFalse(resultOfFindingJunkLaptop.contains(headphones));
        assertFalse(resultOfFindingJunkLaptop.contains(laptopDell));

    }

    @Test
    public void deleteAllProductsByTitleAndDescriptionTest() {
        productDatabase.add(laptopAcer);
        productDatabase.add(laptopDell);
        productDatabase.add(refrigerator);
        productDatabase.add(tv);
        productDatabase.add(headphones);

        boolean resultOfDeletingRefrigeratorAndLaptopAcer = productDatabase.deleteAllByTitleAndDescription("Refrigerator","Acer");

        assertTrue(resultOfDeletingRefrigeratorAndLaptopAcer);
        assertFalse(productDatabase.containsProduct(refrigerator));
        assertFalse(productDatabase.containsProduct(laptopAcer));
        assertTrue(productDatabase.containsProduct(laptopDell));

    }

    @Test
    public void deleteAllProductsByTitleAndPriceRangeTest() {
        productDatabase.add(laptopAcer);
        productDatabase.add(laptopDell);
        productDatabase.add(refrigerator);
        productDatabase.add(tv); // price 3
        productDatabase.add(headphones);

        boolean resultOfDeletingRefrigeratorAndTV = productDatabase.deleteAllByTitleAndPriceRange("Refrigerator",
                1,5);

        assertTrue(resultOfDeletingRefrigeratorAndTV);
        assertFalse(productDatabase.containsProduct(refrigerator));
        assertTrue(productDatabase.containsProduct(laptopAcer));
        assertTrue(productDatabase.containsProduct(laptopDell));
        assertFalse(productDatabase.containsProduct(tv));
    }

    @Test
    public void deleteAllProductsByDescriptionAndPriceRangeTest() {
        productDatabase.add(laptopAcer);
        productDatabase.add(laptopDell);
        productDatabase.add(refrigerator);
        productDatabase.add(tv); // price 3
        productDatabase.add(headphones);

        boolean resultOfDeletingRefrigeratorAndHeadphones = productDatabase.deleteAllByDescriptionAndPriceRange("Beats",
                1,5);

        assertTrue(resultOfDeletingRefrigeratorAndHeadphones);
        assertFalse(productDatabase.containsProduct(headphones));
        assertTrue(productDatabase.containsProduct(laptopAcer));
        assertTrue(productDatabase.containsProduct(laptopDell));
        assertFalse(productDatabase.containsProduct(tv));
    }

    @Test
    public void deleteAllProductsByTitleAndDescriptionAndPriceRangeTest() {
        productDatabase.add(laptopAcer);
        productDatabase.add(laptopDell);
        productDatabase.add(refrigerator);
        productDatabase.add(tabletPc); //price 350
        productDatabase.add(headphones); // price 600

        boolean resultOfDeletingRefrigeratorAndLaptop = productDatabase.deleteAllByTitleAndDescriptionAndPriceRange("Refrigerator",
                "Acer",349,601);

        assertTrue(resultOfDeletingRefrigeratorAndLaptop);
        assertFalse(productDatabase.containsProduct(refrigerator));
        assertFalse(productDatabase.containsProduct(laptopAcer));
        assertFalse(productDatabase.containsProduct(laptopDell));
        assertFalse(productDatabase.containsProduct(tabletPc));
        assertFalse(productDatabase.containsProduct(headphones));

    }
}