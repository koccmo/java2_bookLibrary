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

    @Override
    public boolean deleteById(long id) {
        for (int i = 0; i< productList.size(); i++){
            if (productList.get(i).getId() == id){
                productList.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public void printProducts() {
        if (productList.size()>0) {
            System.out.println("Products in database: ");
            productList.forEach(System.out::println);
        }else{
            System.out.println("Database is empty");
        }
    }


}
