package internet_store.lesson_5.core.services;

import internet_store.lesson_5.core.domain.Product;
import internet_store.lesson_5.core.requests.Ordering;

import java.util.Comparator;
import java.util.List;

import static internet_store.application.core.services.product.validators.FindProductsRequestValidator.*;

public class OrderingProductsService {

    public List<Product> order(List<Product> products, Ordering ordering) {
        Comparator<Product> comparator = getComparator(ordering.getOrderBy());
        if (ORDERING_DIRECTION_2.equals(ordering.getOrderDirection())) {
            comparator = comparator.reversed();
        }

        products.sort(comparator);
        return products;
    }

    private Comparator<Product> getComparator(String fieldName) {
        return switch (fieldName) {
            case ORDERING_NAME_1 -> new NameComparator();
            case ORDERING_NAME_2 -> new DescriptionComparator();
            default -> throw new IllegalArgumentException("Sorting is not supported by field: " + fieldName);
        };
    }

    private class NameComparator implements Comparator<Product> {
        @Override
        public int compare(Product product1, Product product2) {
            return product1.getName().compareTo(product2.getName());
        }
    }

    private class DescriptionComparator implements Comparator<Product> {
        @Override
        public int compare(Product product1, Product product2) {
            return product1.getDescription().compareTo(product2.getDescription());
        }
    }

}