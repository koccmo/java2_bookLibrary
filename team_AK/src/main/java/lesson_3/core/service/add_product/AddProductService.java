package lesson_3.core.service.add_product;

import lesson_3.ProductListApplication;
import lesson_3.core.core_error.CoreError;
import lesson_3.core.domain.Product;
import lesson_3.core.request.add_product.AddProductRequest;
import lesson_3.core.request.add_product.product_items.AddProductDescriptionRequest;
import lesson_3.core.request.add_product.product_items.AddProductPriceRequest;
import lesson_3.core.request.add_product.product_items.AddProductQuantityRequest;
import lesson_3.core.request.add_product.product_items.AddProductTitleRequest;
import lesson_3.core.response.add_product.AddProductResponse;
import lesson_3.core.response.add_product.product_items.AddProductDescriptionResponse;
import lesson_3.core.response.add_product.product_items.AddProductPriceResponse;
import lesson_3.core.response.add_product.product_items.AddProductQuantityResponse;
import lesson_3.core.response.add_product.product_items.AddProductTitleResponse;
import lesson_3.core.service.add_product.product_items_service.AddProductDescriptionService;
import lesson_3.core.service.add_product.product_items_service.AddProductPriceService;
import lesson_3.core.service.add_product.product_items_service.AddProductQuantityService;
import lesson_3.core.service.add_product.product_items_service.AddProductTitleService;
import lesson_3.database.product_database.InnerProductDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddProductService implements ProductUpdate {
    private final InnerProductDatabase productDatabase;

    public AddProductService(InnerProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    public AddProductResponse execute(AddProductRequest addProductRequest) {
        List<CoreError> errors = new ArrayList<>();
        AddProductTitleService titleService = new AddProductTitleService();
        AddProductDescriptionService descriptionService = new AddProductDescriptionService();
        AddProductQuantityService quantityService = new AddProductQuantityService();
        AddProductPriceService priceService = new AddProductPriceService();

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
        if (isTitleExist(addProductRequest)) {
            errors.add(new CoreError("Title input error: ", "Duplicate"));
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

    private boolean isTitleExist(AddProductRequest request) {
        return ProductListApplication.findProductService.isProductTitleExist(productDatabase.getAllProducts(),
                request.getProduct().getTitle());
    }
}