package internet_store.database.product;

import internet_store.domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductDatabaseImpl implements ProductDatabase{

    private Long id= 1L;
    private List<Product> productList = new ArrayList<>();

    @Override
    public List <Product> getProducts(){
        return productList;
    }

    @Override
    public void add(Product product) {
            product.setId(id);
            productList.add(product);
            id++;
    }

    @Override
    public void deleteById(long id) {
        for (int i = 0; i< productList.size(); i++){
            if (productList.get(i).getId() == id){
                productList.remove(i);
            }
        }
    }

    @Override
    public void changeTitle(long id, String newTitle) {
        for (int i = 0; i < productList.size(); i++){
            if (productList.get(i).getId() == id) {
                productList.get(i).setTitle(newTitle);
            }
        }
    }

    @Override
    public void changeDescription(long id, String newDescription) {
        for (int i = 0; i < productList.size(); i++){
            if (id == productList.get(i).getId()){
                productList.get(i).setDescription(newDescription);
            }
        }
    }

    @Override
    public Optional<Product> findAnyByTitle(String title) {
        return productList.stream()
                .filter(product -> product.getTitle().toLowerCase().startsWith(title.toLowerCase()))
                .findAny();
    }

    @Override
    public List<Product> findAllByTitle(String title) {
        return productList.stream()
                .filter(product -> product.getTitle().toLowerCase().startsWith(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    /*public Optional<Product> findById(Long id) {
        for (int i = 0; i < productList.size(); i++) {
            Product listOfProducts = productList.get(i);
            if (listOfProducts.getId() == id) {
                return Optional.of(listOfProducts);
            }
        }
        return Optional.empty();
    }*/

    @Override
    public Optional<Product> findById(Long id) {
        return productList.stream()
            .filter(product -> product.getId() == id)
                .findAny();
    }
}
