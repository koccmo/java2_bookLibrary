package internet_store.core.domain;
import org.junit.Test;

import static org.junit.Assert.*;

public class SorterTest {

    @Test
    public void testByPriceAscending() {

        Product[] products = new Product[]{
                new Product("Mobile phone", "Samsung", 350),
                new Product("TV", "LG", 450),
                new Product("Headphones", "Beats", 200)
        };

        Sorter sorterByPriceAscending = new Sorter(products);
        Product[] productsAfterSorting = sorterByPriceAscending.sortByPriceAsc();
        assertEquals(productsAfterSorting.length,3);
        assertEquals(productsAfterSorting[0].getPrice(),200);
        assertEquals(productsAfterSorting[1].getPrice(),350);
        assertEquals(productsAfterSorting[2].getPrice(),450);

        assertFalse(productsAfterSorting[0].getPrice() == 350);
        assertFalse(productsAfterSorting[1].getPrice() == 450);
        assertFalse(productsAfterSorting[2].getPrice() == 200);
    }

    @Test
    public void testByPriceDescending() {

        Product[] products = new Product[]{
                new Product("Mobile phone", "Samsung", 350),
                new Product("TV", "LG", 450),
                new Product("Headphones", "Beats", 200)
        };

        Sorter sorterByPriceDescending = new Sorter(products);
        Product[] productsAfterSorting = sorterByPriceDescending.sortByPriceDesc();
        assertEquals(productsAfterSorting.length,3);
        assertEquals(productsAfterSorting[0].getPrice(),450);
        assertEquals(productsAfterSorting[1].getPrice(),350);
        assertEquals(productsAfterSorting[2].getPrice(),200);

        assertFalse(productsAfterSorting[0].getPrice() == 350);
        assertFalse(productsAfterSorting[1].getPrice() == 450);
    }


}
