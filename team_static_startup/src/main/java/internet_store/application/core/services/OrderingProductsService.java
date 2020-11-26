package internet_store.application.core.services;

import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.Ordering;

import java.util.*;

import static internet_store.application.core.services.validators.FindProductsRequestValidator.*;

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