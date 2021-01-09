package internet_store.database.product;

import internet_store.core.domain.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductDatabaseImpl implements ProductDatabase{

    private Long id= 1L;
    private final List<Product> productList = new ArrayList<>();

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
    public void deleteById(Long id) {
        productList.removeIf(product -> product.getId().equals(id));
    }

    @Override
    public void changeTitle(Long id, String newTitle) {
        for (Product product : productList) {
            if (product.getId().equals(id)) {
                product.setTitle(newTitle);
            }
        }
    }

    @Override
    public void changeDescription(Long id, String newDescription) {
        for (Product product : productList) {
            if (id.equals(product.getId())) {
                product.setDescription(newDescription);
            }
        }
    }

    @Override
    public void changePrice(Long id, Integer newPrice) {
        for (Product product : productList) {
            if (id.equals(product.getId())) {
                product.setPrice(newPrice);
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
            .filter(product -> product.getId().equals(id))
                .findAny();
    }

    @Override
    public List<Product> findAllByPriceRange(Integer startPrice, Integer endPrice) {
        return productList.stream()
                .filter(product -> product.getPrice() > startPrice && product.getPrice() < endPrice)
                .collect(Collectors.toList());
    }

    //TODO
    //TODO tut nado esjo 3 metoda dobavitj

    @Override
    public boolean containsProduct(Product product) {
        return productList.stream()
                .anyMatch(product1 -> product1.equals(product));
    }

    @Override
    public boolean containsId(Long id) {
        return productList.stream()
                .anyMatch(product -> product.getId().equals(id));
    }
}
