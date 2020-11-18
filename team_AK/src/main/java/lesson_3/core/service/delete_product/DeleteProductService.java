package lesson_3.core.service.delete_product;

import lesson_3.ProductListApplication;
import lesson_3.core.core_error.CoreError;
import lesson_3.core.domain.Product;
import lesson_3.core.request.delete_product.DeleteProductRequest;
import lesson_3.core.response.delete_product.DeleteProductResponse;
import lesson_3.core.validate.NegativeNumberValidator;
import lesson_3.database.product_database.InnerProductDatabase;

import java.util.List;

public class DeleteProductService {
    private final InnerProductDatabase productDatabase;

    public DeleteProductService(InnerProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    public DeleteProductResponse execute(DeleteProductRequest deleteProductRequest) {
        NegativeNumberValidator<?> negativeNumberValidator = new NegativeNumberValidator<>(deleteProductRequest.getId());

        List<CoreError> errors = negativeNumberValidator.validate();

        List<Product> products = productDatabase.getAllProducts();

        if (isIdExist(products, deleteProductRequest.getId())) {
            Product deletedProduct = findProductById(products, deleteProductRequest.getId());
            ProductListApplication.productDatabase.deleteProduct(deletedProduct);
        } else {
            errors.add(new CoreError("Id error ", "wrong ID"));
        }

        if (errors.isEmpty()) {
            return new DeleteProductResponse(deleteProductRequest.getId());
        }
        return new DeleteProductResponse(errors);
    }

    private boolean isIdExist(List<Product> products, long id) {
        return ProductListApplication.findProductService.isIdExist(products, id);
    }

    private Product findProductById(List<Product> products, long id) {
        return ProductListApplication.findProductService.findById(products, id);
    }
}