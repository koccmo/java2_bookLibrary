package estore.core.service;

import java.util.List;
import estore.core.domain.Product;

public class PrintListService {

    public static void printListOfProducts(List<Product> products) {
        System.out.println("");
        System.out.println("List of products");
        if (products.size() == 0) {
            System.out.println("No products available");
            return;
        }
        for (Product product : products) {
            System.out.println(product);
        }
    }

}
