package team_static_startup.igors_test;

import java.util.*;

public class ProductDatabaseImpl implements ProductDatabase {
    private Long id = 1L;
    private final List<Product> productList = new ArrayList<>();

    @Override
    public Long save(Product product) {
        product.setId(id);
        productList.add(product);
        return ++id;
    }

    @Override
    public boolean delete(Long productId) {
        for (Product product : productList) {
            if (product.getId().equals(productId)) {
                productList.remove(product);
                return true;
            }
        }
        return false;
    }

}
