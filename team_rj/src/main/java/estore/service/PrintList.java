package estore.service;

import estore.domain.Product;

import java.util.List;

public class PrintList {
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
