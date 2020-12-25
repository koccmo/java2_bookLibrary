package internet_store.core.services;

import internet_store.core.domain.Product;
import internet_store.core.requests.Paging;

import java.util.List;
import java.util.stream.Collectors;

public class PagingProductService {

    public List<Product> page(List<Product> products, Paging paging) {
        if(paging != null) {
            int numberProductsToSkip = paging.getPageSize() * (paging.getPageNumber() - 1);
            return products.stream()
                    .skip(numberProductsToSkip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return products;
        }
    }
}
