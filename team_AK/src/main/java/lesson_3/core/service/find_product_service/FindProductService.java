package lesson_3.core.service.find_product_service;


import lesson_3.core.domain.Product;

import java.util.List;
import java.util.stream.IntStream;

public class FindProductService {
    private final Product EMPTY_OBJECT = null;

    public boolean isIdExist(List<Product> products, long id) {
        return products.stream().anyMatch(pr -> pr.getId() == id);
    }

    public Product findById(List<Product> products, long id) {
        return products.stream().filter(pr -> pr.getId() == id).findFirst().orElse(EMPTY_OBJECT);
    }

    public int findProductIndex(List<Product> products, long id) {
        int LIST_FIRST_ELEMENT = 0;
        int NO_ID_FIND = -1;
        return IntStream.range(LIST_FIRST_ELEMENT, products.size())
                .filter(i -> products.get(i).getId() == id)
                .findFirst().orElse(NO_ID_FIND);
    }

    public boolean isProductTitleExist(List<Product> products, String title) {
        return products.stream().anyMatch(i -> i.getTitle().equals(title));
    }
}