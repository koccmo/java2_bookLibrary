import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductRepository {

    private static ProductRepository productRepository;
    private static List<Product> productList;

    /**
     * Class constructor, initialize product list with default products.
     */
    private ProductRepository (){
        initializeProductList();
    }

    /**
     * Method to return ProductRepository instance.
     */
    public static ProductRepository getProductRepository() {
        if (productRepository == null){
            productRepository = new ProductRepository();
        }
        return productRepository;
    }

    /**
     * Method to initialize product list with default products.
     */
    public List<Product> getProductList(){
        return productList;
    }

    /**
     * Method to initialize product list with default products.
     */
    private void initializeProductList(){
        productList = new ArrayList<>();
        Product productOne = new Product("laptop", "standard laptop with 15\" display", 800.0);
        Product productTwo = new Product("mouse", "wireless mouse", 20.0);
        Product productThree = new Product("keyboard", "wireless keyboard", 40.0);
        productList.add(productOne);
        productList.add(productTwo);
        productList.add(productThree);
    }

    /**
     * Method to add new product to the store, if the similar product does not exist.
     */
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
            System.out.println("Product already exists in the store.");
        }
    }

    /**
     * Method to remove product by name.
     */
    public void removeProductByName(final String name){
        Product productToRemove = findProductByName(name);
        if (productToRemove != null) {
            productList.remove(productToRemove);
        }
    }

    /**
     * Method to remove product by ID.
     */
    public void removeProductById(final long id){
        Product productToRemove = findProductById(id);
        if (productToRemove != null) {
            productList.remove(productToRemove);
        }
    }

    /**
     * Method to find product by name.
     */
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

    /**
     * Method to find product by ID.
     */
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

    /**
     * Method to print out all the products form the store.
     */
    public void printOutAllProducts(){
        if (productList.isEmpty()) {
            System.out.println("Store is empty now.");
        } else {
            Collections.sort(productList, Product.idComparator);
            productList.stream().forEach(product -> System.out.println(product));
        }
    }

    /**
     * Method to update product info by 'ID'.
     */
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
