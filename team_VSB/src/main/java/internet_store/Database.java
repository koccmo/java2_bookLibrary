package internet_store;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Database {
    private static Database productRepository;
    private static List<Product> productList;

    private Database (){
        initializeProductList();
    }

    public static Database getDatabase() {
        if (productRepository == null){
            productRepository = new Database();
        }
        return productRepository;
    }

    public List<Product> getProductList(){
        return productList;
    }

    private void initializeProductList(){
        productList = new ArrayList<>();
        Product productOne = new Product("Camera", "Sony 6300", 600.0);
        Product productTwo = new Product("Gimbal", "DJI RS2", 900.0);
        Product productThree = new Product("Lense", "Sigma 30 mm", 200.0);
        productList.add(productOne);
        productList.add(productTwo);
        productList.add(productThree);
    }

    public void addProduct(final String name, final String description, final double price){
        Product productFound = productList.stream()
                .filter(product -> name.equals(product.getName())
                        && description.equals(product.getDescription())
                        && price == product.getPrice())
                .findAny()
                .orElse(null);
        if (productFound == null){
            productList.add(new Product(name, description, price));
        } else {
            System.out.println("Product exists in the store.");
        }
    }

    public void removeProductByName(final String name){
        Product productToRemove = findProductByName(name);
        if (productToRemove != null) {
            productList.remove(productToRemove);
        }
    }

    public void removeProductById(final long id){
        Product productToRemove = findProductById(id);
        if (productToRemove != null) {
            productList.remove(productToRemove);
        }
    }

    public Product findProductByName(final String name) {
        Product productFound = productList.stream()
                .filter(product -> name.equals(product.getName()))
                .findAny()
                .orElse(null);
        if (productFound == null) {
            System.out.println("Product is not found.");
        }
        return productFound;
    }

    public Product findProductById(final long id) {
        Product productFound = productList.stream()
                .filter(product -> id == product.getId())
                .findAny()
                .orElse(null);
        if (productFound == null) {
            System.out.println("Product is not found.");
        }
        return productFound;
    }

    public void printOutAllProducts(){
        if (productList.isEmpty()) {
            System.out.println("Store is empty.");
        } else {
            Collections.sort(productList, Product.idComparator);
            productList.stream().forEach(product -> System.out.println(product));
        }
    }

    public void updateProductById(final long id,
                                  final String newName,
                                  final String newDescription,
                                  final double newPrice){
        Product productFound = findProductById(id);
        if (productFound != null) {
            productFound.setName(newName);
            productFound.setDescription(newDescription);
            productFound.setPrice(newPrice);
            System.out.println("Product is updated.");
        }
    }
}
