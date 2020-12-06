package internet_store.core.services;

import internet_store.core.domain.Product;
import internet_store.core.requests.Ordering;

import java.util.Comparator;
import java.util.List;

import static internet_store.core.services.validators.FindProductRequestValidator.*;

public class OrderingProductService {

    public List<Product> order(List<Product> products, Ordering ordering) {
        Comparator<Product> comparator = getComparator(ordering.getOrderBy());
        if(ORDERING_DIRECTION_2.equals(ordering.getOrderDirection())) {
            comparator = comparator.reversed();
        }
        products.sort(comparator);
        return products;

    }

    private Comparator<Product> getComparator(String fieldName) {
        return switch (fieldName) {
            case ORDERING_NAME_1 ->  new NameComparator();
            case ORDERING_NAME_2 -> new DescriptionComparator();
            default -> throw new IllegalArgumentException("Sort is not supported by field: " + fieldName);
        };
    }

    private class NameComparator implements Comparator<Product> {
        @Override
        public int compare(Product o1, Product o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    private class DescriptionComparator implements Comparator<Product> {
        @Override
        public int compare(Product o1, Product o2) {
            return o1.getDescription().compareTo(o2.getDescription());
        }
    }
}
