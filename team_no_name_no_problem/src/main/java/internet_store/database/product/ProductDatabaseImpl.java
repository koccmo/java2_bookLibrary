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
        //return session().createCriteria(Product.class).list()
        return productList;
    }

    @Override
    public void add(Product product) {
        //session().save(product);
            product.setId(id);
            productList.add(product);
            id++;
    }

    @Override
    public boolean deleteById(Long id) {
        //session().remove(id);
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
        //Product product = new Product();
        //        product.setTitle(newTitle);
        //        session().update(product);
        for (Product product : productList) {
            if (product.getId().equals(id)) {
                product.setTitle(newTitle);
            }
        }
    }

    @Override
    public void changeDescription(Long id, String newDescription) {
        //Product product = new Product();
        //        product.setDescription(newDescription);
        //        session().update(product);
        for (Product product : productList) {
            if (id.equals(product.getId())) {
                product.setDescription(newDescription);
            }
        }
    }

    @Override
    public void changePrice(Long id, Integer newPrice) {
        // Product product = new Product();
        //        product.setPrice(newPrice);
        //        session().update(product);
        for (Product product : productList) {
            if (id.equals(product.getId())) {
                product.setPrice(newPrice);
            }
        }
    }

    @Override
    public List<Product> findAllByTitle(String title) {
        //Criteria criteria = session().createCriteria(Product.class);
        //        criteria.add(Restrictions.eq(title, title));
        //        criteria.uniqueResult();
        //        ListProduct result = criteria.list();
        //        return result;
        //        Product product = (Product) session().createCriteria(Product.class)
        //               .add(Restrictions.eq(title, title))
        //               .uniqueResult();
        return productList.stream()
                .filter(product -> product.getTitle().toLowerCase().startsWith(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllByDescription(String description) {
        //Criteria criteria = session().createCriteria(Product.class);
        //        criteria.add(Restrictions.eq(description, description));
        //        criteria.uniqueResult();
        //        ListProduct result = criteria.list();
        //        return result;
        return productList.stream()
                .filter(product -> product.getDescription().toLowerCase().startsWith(description.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllByPriceRange(Integer startPrice, Integer endPrice) {
        return productList.stream()
                .filter(product -> product.getPrice() >= startPrice && product.getPrice() <= endPrice)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllByTitleAndDescription(String title, String description) {
        //Criteria criteria = session().createCriteria(Product.class);
        //        Criterion productTitle = Restrictions.eq(title, title);
        //        Criterion productDescription = Restrictions.eq(description, description);
        //        LogicalExpression orExpression = Restrictions.or(productTitle,productDescription);
        //        criteria.add(orExpression);
        //        List result = criteria.list();
        //        return result;
        return productList.stream()
                .filter(product -> product.getTitle().toLowerCase().startsWith(title.toLowerCase()) &&
                        product.getDescription().toLowerCase().startsWith(description.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllByTitleAndDescriptionAndPriceRange(String title, String description, Integer startPrice, Integer endPrice) {
        return productList.stream()
                .filter(product -> product.getTitle().toLowerCase().startsWith(title.toLowerCase()) &&
                        product.getDescription().toLowerCase().startsWith(description.toLowerCase()) &&
                        product.getPrice() >= startPrice && product.getPrice() <= endPrice)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllByDescriptionAndPriceRange(String description, Integer startPrice, Integer endPrice) {
        return productList.stream()
                .filter(product -> product.getDescription().toLowerCase().startsWith(description.toLowerCase()) &&
                        product.getPrice() >= startPrice && product.getPrice() <= endPrice)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllByTitleAndPriceRange(String title, Integer startPrice, Integer endPrice) {
        return productList.stream()
                .filter(product -> product.getTitle().toLowerCase().startsWith(title.toLowerCase()) &&
                        product.getPrice() >= startPrice && product.getPrice() <= endPrice)
                .collect(Collectors.toList());
    }


    @Override
    public Optional<Product> findById(Long id) {
        //Product product = (Product) session().createCriteria(Product.class)
        //                .add(Restrictions.eq(id, id))
        //                .uniqueResult();
        //        return Optional.ofNullable(product);
        return productList.stream()
            .filter(product -> product.getId().equals(id))
                .findAny();
    }

    @Override
    public boolean containsProduct(Product product) {
        //return session().contains(product);
        return productList.stream()
                .anyMatch(product1 -> product1.equals(product));
    }

    @Override
    public boolean containsId(Long id) {
        // return session().contains(id);
        return productList.stream()
                .anyMatch(product -> product.getId().equals(id));
    }

    @Override
    public boolean containsTitle(String title) {
        // return session().contains(title);
        return productList.stream()
                .anyMatch(product -> product.getTitle().equals(title));
    }

    @Override
    public boolean containsDescription(String description) {
        // return session().contains(description);
        return productList.stream()
                .anyMatch(product -> product.getDescription().equals(description));
    }
}
