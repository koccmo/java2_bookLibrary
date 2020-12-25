package internet_store.core.service.product;

import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Product;
import internet_store.core.request.product.AddProductRequest;
import internet_store.core.request.product.product_items.AddProductDescriptionRequest;
import internet_store.core.request.product.product_items.AddProductPriceRequest;
import internet_store.core.request.product.product_items.AddProductQuantityRequest;
import internet_store.core.request.product.product_items.AddProductTitleRequest;
import internet_store.core.response.product.AddProductResponse;
import internet_store.core.response.product.product_item.AddProductDescriptionResponse;
import internet_store.core.response.product.product_item.AddProductPriceResponse;
import internet_store.core.response.product.product_item.AddProductQuantityResponse;
import internet_store.core.response.product.product_item.AddProductTitleResponse;
import internet_store.database.product_database.InnerProductDatabase;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddProductService implements ProductUpdate {
    @Autowired
    InnerProductDatabase productDatabase;
    final AddProductTitleService titleService = new AddProductTitleService();
    final AddProductDescriptionService descriptionService = new AddProductDescriptionService();
    final AddProductQuantityService quantityService = new AddProductQuantityService();
    final AddProductPriceService priceService = new AddProductPriceService();

    public AddProductResponse execute(AddProductRequest addProductRequest) {
        List<CoreError> errors = new ArrayList<>();

        AddProductTitleResponse titleResponse = titleService.execute(new AddProductTitleRequest
                (addProductRequest.getProduct().getTitle()));
        AddProductDescriptionResponse descriptionResponse = descriptionService.execute(new AddProductDescriptionRequest
                (addProductRequest.getProduct().getDescription()));
        AddProductQuantityResponse quantityResponse = quantityService.execute(new AddProductQuantityRequest
                (addProductRequest.getProduct().getQuantity()));
        AddProductPriceResponse priceResponse = priceService.execute(new AddProductPriceRequest
                (addProductRequest.getProduct().getPrice()));

        if (titleResponse.hasErrors()) {
            errors.add(new CoreError("Title input error: ", "Empty field"));
        }
        if (descriptionResponse.hasErrors()) {
            errors.add(new CoreError("Description input error: ", "Empty field"));
        }
        if (quantityResponse.hasErrors()) {
            errors.add(new CoreError("Quantity input error: ", "Negative number"));
        }
        if (priceResponse.hasErrors()) {
            errors.add(new CoreError("Price input error: ", "Negative number"));
        }

        execute(errors, addProductRequest.getProduct());

        return new AddProductResponse(errors);
    }

    @Override
    public void execute(List<CoreError> errors, Product product) {
        if (errors.isEmpty()) {
            productDatabase.addProduct(product);
        }
    }
}