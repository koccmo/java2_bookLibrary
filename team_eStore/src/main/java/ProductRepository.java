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
     * Method to add product to the store if not present similar product.
     */
    public void addProduct(final String name, final String description, final double price){
        Product product = new Product(name, description, price);
        boolean containsProduct = false;
        for (Product productTemp : productList) {
            if (product.equals(productTemp)) {
                containsProduct = true;
            }
        }
        if (!containsProduct) {
            productList.add(product);
        }
    }

    /**
     * Method to remove product form the store by name.
     */
    public void removeProductByName(final String name){
        Product productToRemove = findProductByName(name);
        if (productToRemove != null) {
            productList.remove(productToRemove);
        }
    }

    /**
     * Method to remove product form the store by ID.
     */
    public void removeProductById(final long id){
        Product productToRemove = findProductById(id);
        if (productToRemove != null) {
            productList.remove(productToRemove);
        }
    }

    /**
     * Method to find product by the name.
     */
    public Product findProductByName(final String name) {
        Product foundProduct = null;
        for (Product product : productList) {
            if (name.equals(product.getName())) {
                foundProduct = product;
            }
        }
        return foundProduct;
    }

    /**
     * Method to find product by the ID.
     */
    public Product findProductById(final long id) {
        Product foundProduct = null;
        for (Product product : productList) {
            if (id == product.getId()) {
                foundProduct = product;
            }
        }
        return foundProduct;
    }

    /**
     * Method to print out all the list of products form the store.
     */
    public void printOutAllProducts(){
        if (productList.isEmpty()) {
            System.out.println("Store is empty now.");
        } else {
            Collections.sort(productList, Product.idComparator);
            for (Product product : productList) {
                System.out.println(product);
            }
        }
    }

    /**
     * Method to update product info by 'ID'.
     */
    public void updateProductById(final long id,
                              final String newName,
                              final String newDescription,
                              final double newPrice){
        Product foundProduct = findProductById(id);
        foundProduct.setName(newName);
        foundProduct.setDescription(newDescription);
        foundProduct.setPrice(newPrice);
    }
}
