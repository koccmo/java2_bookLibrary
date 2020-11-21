package internet_store.database.product;

import internet_store.core.domain.Product;

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
        productList.removeIf(product -> product.getId() == id);
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
    public List<Product> findAllByTitle(String title) {
        return productList.stream()
                .filter(product -> product.getTitle().toLowerCase().startsWith(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllByDescription(String description) {
        return productList.stream()
                .filter(product -> product.getDescription().toLowerCase().startsWith(description.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllByTitleAndDescription(String title, String description) {
        return productList.stream()
                .filter(product -> product.getTitle().toLowerCase().startsWith(title.toLowerCase()) &&
                        product.getDescription().toLowerCase().startsWith(description.toLowerCase()))
                .collect(Collectors.toList());
    }


    @Override
    public Optional<Product> findById(Long id) {
        return productList.stream()
            .filter(product -> product.getId() == id)
                .findAny();
    }
}
