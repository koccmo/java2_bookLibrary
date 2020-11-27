package lesson_2.service;

import internet_store.core.domain.Product;

import java.util.List;
import java.util.stream.IntStream;

public class FindProductService {
    final int NO_ID_FIND = -1;
    final int LIST_FIRST_ELEMENT = 0;
    final Product EMPTY_OBJECT = null;

    public boolean isIdExist(List<Product> products, long id) {
        return products.stream().anyMatch(pr -> pr.getId() == id);
    }

    public Product findById(List<Product> products, long id) {
        return products.stream().filter(pr -> pr.getId() == id).findFirst().orElse(EMPTY_OBJECT);
    }

    public int findProductIndex(List<Product> products, long id) {
        return IntStream.range(LIST_FIRST_ELEMENT, products.size())
                .filter(i -> products.get(i).getId() == id)
                .findFirst()
                .orElse(NO_ID_FIND);
    }
}