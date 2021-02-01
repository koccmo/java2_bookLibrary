package internet_store.core.service.product;

import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Product;
import internet_store.core.request.product.CheckStockQuantityRequest;
import internet_store.core.response.product.CheckStockQuantityResponse;

import internet_store.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CheckStockQuantityService {
    @Autowired
    ProductRepository productRepository;

    public CheckStockQuantityResponse execute(CheckStockQuantityRequest request) {
        List<CoreError> errors = new ArrayList<>();

        Product productInStock = productRepository.findByTitle(request.getTitle());
        Long userQuantity = request.getQuantity();
        Long stockQuality = productInStock.getQuantity();

        if (userQuantity > stockQuality) {
            errors.add(new CoreError("Quantity error", "Product " + productInStock.getTitle()
                    + " Available only " + stockQuality));
        }
        return new CheckStockQuantityResponse(errors);
    }
}