package internet_store;

import java.util.ArrayList;
import java.util.List;

public class ProductDatabaseImpl implements ProductDatabase{

    private Long id= 1L;
    private List<Product> productList = new ArrayList<>();

    List <Product> getProductList(){
        return productList;
    }

    @Override
    public boolean save(Product product) {
        if (productList.contains(product)){
            return false;
        }else{
            product.setId(id);
            productList.add(product);
            id++;
        }
        return true;
    }


}
