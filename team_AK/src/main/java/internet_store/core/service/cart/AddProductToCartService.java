package internet_store.core.service.cart;

import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Product;
import internet_store.core.request.cart.AddProductToCartRequest;
import internet_store.core.response.cart.AddProductToCartResponse;
import internet_store.core.validate.ProductQuantityValidator;
import internet_store.database.cart_database.InnerCartDatabase;
import internet_store.database.product_database.InnerProductDatabase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AddProductToCartService {
    private final InnerProductDatabase productDatabase;
    private final InnerCartDatabase cartDatabase;

    public AddProductToCartService(InnerProductDatabase productDatabase, InnerCartDatabase cartDatabase) {
        this.productDatabase = productDatabase;
        this.cartDatabase = cartDatabase;
    }

    public AddProductToCartResponse execute(AddProductToCartRequest productToCartRequest) {
        List<CoreError> errors = new ArrayList<>();

        long productId = productToCartRequest.getId();

        boolean isProductIdExist = productDatabase.isIdExist(productId);
        boolean isProductIdExistInCart = cartDatabase.isIdExist(productId);

        if (isProductIdExistInCart) {
            errors.add(new CoreError("Product error ", "Product exist in cart"));
            return new AddProductToCartResponse(errors);
        }

        if (isProductIdExist) {
            Product findProductAddToCart = productDatabase.findById(productId);
            BigDecimal quantityToCart = productToCartRequest.getNewQuantity();
            errors = validateQuantity(findProductAddToCart, quantityToCart);
        } else {
            errors.add(new CoreError("Id error ", "Wrong Id"));
        }

        if (errors.isEmpty()) {
            return new AddProductToCartResponse(productId);
        }
        return new AddProductToCartResponse(errors);
    }

    private List<CoreError> validateQuantity(Product product, BigDecimal quantityToCart) {
        ProductQuantityValidator quantityValidator = new ProductQuantityValidator();
        List<CoreError> errors = quantityValidator.validate(product.getQuantity(), quantityToCart);
        if (errors.isEmpty()) {
            AddedProduct addedProduct = new AddedProduct(product, quantityToCart);
            addToCart(addedProduct);
        }
        return errors;
    }

    private void addToCart(AddedProduct addedProduct) {
        Product copyProductToCart = new Product(addedProduct.getProduct());
        BigDecimal newQuantity = addedProduct.getNewQuantity();

        copyProductToCart.setQuantity(newQuantity);
        BigDecimal productSum = copyProductToCart.getPrice().multiply(newQuantity);
        copyProductToCart.setSum(productSum);
        cartDatabase.addProductToCart(copyProductToCart);
    }
}