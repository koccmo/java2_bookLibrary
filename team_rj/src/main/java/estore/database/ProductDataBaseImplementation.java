package estore.database;

import java.util.ArrayList;
import java.util.List;
import estore.domain.Product;
import estore.domain.ProductCategory;

public class ProductDataBaseImplementation implements ProductDataBase {

    private Long nextProductId;
    private List<Product> products = new ArrayList<>();

    public ProductDataBaseImplementation() {
        initializeListOfProducts();
        setNextProductId();
    }

    @Override
    public List<Product> getDatabase() {
        return products;
    }

    @Override
    public int getDatabaseSize() {
        return products.size();
    }

    private void initializeListOfProducts() {
        this.products.add(new Product("Apple", "Juicy red apples", "Fruits"));
        this.products.add(new Product("Melon", "Melons from Georgia", "Fruits"));
        this.products.add(new Product("Grapes", "Small blue grapes", "Fruits"));
        this.products.add(new Product("Beer", "Aldaris Pilzenes", "Alcohol"));
        this.products.add(new Product("Beer", "1664 Blanc Alcohol Free", "Alcohol"));
        this.products.add(new Product("Salmon", "Free Baltic salmon", "Fish"));
        products.get(0).setId(1L);
        products.get(1).setId(2L);
        products.get(2).setId(3L);
        products.get(3).setId(4L);
        products.get(4).setId(5L);
        products.get(5).setId(6L);
    }

    private void setNextProductId() {
        nextProductId = Long.valueOf(products.size()) + 1;
    }

    @Override
    public List<Product> searchProductByName(String name) {
        List<Product> foundProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toLowerCase().equals(name.toLowerCase())) {
                foundProducts.add(product);
            }
        }
        return foundProducts;
    }

    @Override
    public List<Product> searchProductByCategory(String category) {
        List<Product> foundProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().toLowerCase().equals(category.toLowerCase())) {
                foundProducts.add(product);
            }
        }
        return foundProducts;
    }

    @Override
    public boolean addNewProduct(Product product) {
        product.setId(nextProductId);
        this.nextProductId++;
        products.add(product);
        return true;
    }

    @Override
    public int removeProductByName(String name) {
        int productsRemoved = 0;
        for (int i = products.size() - 1; i >=0; i--) {
            if (products.get(i).getName().toLowerCase().equals(name.toLowerCase())) {
                products.remove(products.get(i));
                productsRemoved++;
            }
        }
        return productsRemoved;
    }

    @Override
    public int removeProductById(Long id) {
        int productsRemoved = 0;
        for (int i = products.size() - 1; i >=0; i--) {
            if (products.get(i).getId().equals(id)) {
                products.remove(products.get(i));
                productsRemoved++;
            }
        }
        return productsRemoved;
    }
}
