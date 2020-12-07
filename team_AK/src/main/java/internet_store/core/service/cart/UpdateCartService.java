package internet_store.core.service.cart;

import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Product;
import internet_store.core.request.cart.UpdateCartRequest;
import internet_store.core.response.cart.UpdateCartResponse;
import internet_store.core.validate.ProductQuantityValidator;
import internet_store.database.cart_database.InnerCartDatabase;
import internet_store.database.product_database.InnerProductDatabase;
import dependency.annotation.DIComponent;
import dependency.annotation.DIDependency;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@DIComponent
public class UpdateCartService {
    @DIDependency
    InnerProductDatabase productDatabase;
    @DIDependency
    InnerCartDatabase cartDatabase;

    public UpdateCartService() {
    }

    public UpdateCartService(InnerProductDatabase productDatabase, InnerCartDatabase cartDatabase) {
        this.productDatabase = productDatabase;
        this.cartDatabase = cartDatabase;
    }

    public UpdateCartResponse execute(UpdateCartRequest updateCartRequest) {
        List<CoreError> errors = new ArrayList<>();

        long productId = updateCartRequest.getId();

        boolean isProductIdExist = productDatabase.isIdExist(productId);

        if (isProductIdExist) {
            Product productInCart = cartDatabase.findById(productId);
            int index = cartDatabase.findProductIndex(productId);

            Product productInDatabase = productDatabase.findByTitle(productInCart.getTitle());

            BigDecimal productQuantity = productInDatabase.getQuantity();
            BigDecimal newQuantity = updateCartRequest.getNewQuantity();

            errors = validateQuantity(productInCart, productQuantity, newQuantity, index);

        } else {
            errors.add(new CoreError("Id error ", "Wrong Id"));
        }

        if (errors.isEmpty()) {
            return new UpdateCartResponse(productId);
        }
        return new UpdateCartResponse(errors);
    }

    private List<CoreError> validateQuantity(Product product, BigDecimal productQuantity, BigDecimal newQuantity, int index) {
        ProductQuantityValidator quantityValidator = new ProductQuantityValidator();
        List<CoreError> errors = quantityValidator.validate(productQuantity, newQuantity);
        if (errors.isEmpty()) {
            UpdatedProduct updatedProduct = new UpdatedProduct(product, newQuantity, index);
            updateCart(updatedProduct);
        }
        return errors;
    }

    private void updateCart(UpdatedProduct updatedProduct) {
        Product product = updatedProduct.getProduct();
        BigDecimal newQuantity = updatedProduct.getNewQuantity();
        int index = updatedProduct.getIndexInCartDatabase();

        product.setQuantity(newQuantity);
        BigDecimal productSum = product.getPrice().multiply(newQuantity);
        product.setSum(productSum);
        cartDatabase.updateCart(index, product);
    }
}