package internet_store.database.product;

import internet_store.core.domain.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Component
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
    public boolean deleteById(Long id) {
        if (productList.removeIf(product -> product.getId().equals(id))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteAllByTitle(String title) {
        if (productList.removeIf(product -> product.getTitle().equals(title))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteAllByDescription(String description) {
        if (productList.removeIf(product -> product.getDescription().equals(description))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteAllByTitleAndDescription(String title, String description) {
        if (productList.removeIf(product -> product.getTitle().equals(title)) &&
                productList.removeIf(product -> product.getDescription().equals(description))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteAllByTitleAndPriceRange(String title, Integer startPrice, Integer endPrice) {
        if (productList.removeIf(product -> product.getTitle().equals(title)) &&
                productList.removeIf(product ->product.getPrice() >= startPrice && product.getPrice() <= endPrice)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteAllByDescriptionAndPriceRange(String description, Integer startPrice, Integer endPrice) {
        if (productList.removeIf(product -> product.getDescription().equals(description)) &&
                productList.removeIf(product ->product.getPrice() >= startPrice && product.getPrice() <= endPrice)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteAllByTitleAndDescriptionAndPriceRange(String title, String description, Integer startPrice, Integer endPrice) {
        if (productList.removeIf(product -> product.getTitle().equals(title)) &&
                productList.removeIf(product -> product.getDescription().equals(description)) &&
                productList.removeIf(product ->product.getPrice() >= startPrice && product.getPrice() <= endPrice)){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteAllByPriceRange(Integer startPrice, Integer endPrice) {
        if (productList.removeIf(product ->product.getPrice() >= startPrice && product.getPrice() <= endPrice)) {
            return true;
        } else {
            return false;
        }
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
    public List<Product> searchAllByTitle(String title) {
        return productList.stream()
                .filter(product -> product.getTitle().toLowerCase().startsWith(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> searchAllByDescription(String description) {
        return productList.stream()
                .filter(product -> product.getDescription().toLowerCase().startsWith(description.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> searchAllByPriceRange(Integer startPrice, Integer endPrice) {
        return productList.stream()
                .filter(product -> product.getPrice() >= startPrice && product.getPrice() <= endPrice)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> searchAllByTitleAndDescription(String title, String description) {
        return productList.stream()
                .filter(product -> product.getTitle().toLowerCase().startsWith(title.toLowerCase()) &&
                        product.getDescription().toLowerCase().startsWith(description.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> searchAllByTitleAndDescriptionAndPriceRange(String title, String description, Integer startPrice, Integer endPrice) {
        return productList.stream()
                .filter(product -> product.getTitle().toLowerCase().startsWith(title.toLowerCase()) &&
                        product.getDescription().toLowerCase().startsWith(description.toLowerCase()) &&
                        product.getPrice() >= startPrice && product.getPrice() <= endPrice)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> searchAllByDescriptionAndPriceRange(String description, Integer startPrice, Integer endPrice) {
        return productList.stream()
                .filter(product -> product.getDescription().toLowerCase().startsWith(description.toLowerCase()) &&
                        product.getPrice() >= startPrice && product.getPrice() <= endPrice)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> searchAllByTitleAndPriceRange(String title, Integer startPrice, Integer endPrice) {
        return productList.stream()
                .filter(product -> product.getTitle().toLowerCase().startsWith(title.toLowerCase()) &&
                        product.getPrice() >= startPrice && product.getPrice() <= endPrice)
                .collect(Collectors.toList());
    }


    @Override
    public Optional<Product> searchById(Long id) {
        return productList.stream()
            .filter(product -> product.getId().equals(id))
                .findAny();
    }

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

    @Override
    public boolean containsTitle(String title) {
        return productList.stream()
                .anyMatch(product -> product.getTitle().equals(title));
    }

    @Override
    public boolean containsDescription(String description) {
        return productList.stream()
                .anyMatch(product -> product.getDescription().equals(description));
    }

    @Override
    public boolean containsPrice(Integer price) {
        return productList.stream()
                .anyMatch(product -> product.getPrice() == price);
    }

    @Override
    public boolean containsTitleAndDescription(String title, String description) {
        return productList.stream()
                .anyMatch(product -> product.getTitle().equals(title) && product.getDescription().equals(description));
    }
}
