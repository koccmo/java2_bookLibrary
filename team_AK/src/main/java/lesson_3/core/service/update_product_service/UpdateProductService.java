package lesson_3.core.service.update_product_service;

import lesson_3.ProductListApplication;
import lesson_3.core.core_error.CoreError;
import lesson_3.core.domain.Product;
import lesson_3.core.request.update_product.UpdateProductRequest;
import lesson_3.core.response.update_product.UpdateProductResponse;
import lesson_3.core.validate.NegativeNumberValidator;
import lesson_3.database.product_database.InnerProductDatabase;

import java.util.List;

public class UpdateProductService {
    private final InnerProductDatabase productDatabase;

    public UpdateProductService(InnerProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    public UpdateProductResponse execute(UpdateProductRequest updateProductRequest) {
        NegativeNumberValidator<?> negativeNumberValidator = new NegativeNumberValidator<>(updateProductRequest.getId());

        List<CoreError> errors = negativeNumberValidator.validate();

        List<Product> products = productDatabase.getAllProducts();

        if (!(isIdExist(products, updateProductRequest.getId()))) {
            errors.add(new CoreError("Id error ", "wrong ID"));
        }

        if (errors.isEmpty()) {
            return new UpdateProductResponse(updateProductRequest.getId());
        }
        return new UpdateProductResponse(errors);
    }

    private boolean isIdExist(List<Product> products, long id) {
        return ProductListApplication.findProductService.isIdExist(products, id);
    }
}