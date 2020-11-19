package lesson_3.core.service.add_product_to_cart;


import lesson_3.core.core_error.CoreError;
import lesson_3.core.domain.Cart;
import lesson_3.core.domain.Product;
import lesson_3.core.request.add_product_to_cart.AddProductToCartRequest;
import lesson_3.core.response.add_product_to_cart.AddProductToCartResponse;
import lesson_3.core.service.find_product_service.FindProductService;
import lesson_3.core.validate.ProductQuantityValidator;
import lesson_3.database.product_database.InnerProductDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddProductToCartService {
    private final InnerProductDatabase productDatabase;
    private final Cart cart;

    public AddProductToCartService(InnerProductDatabase productDatabase, Cart cart) {
        this.productDatabase = productDatabase;
        this.cart = cart;
    }

    public AddProductToCartResponse execute(AddProductToCartRequest productToCartRequest) {
        List<CoreError> errors = new ArrayList<>();
        FindProductService findProductService = new FindProductService();

        long productId = productToCartRequest.getId();
        List<Product> getAllProducts = productDatabase.getAllProducts();

        boolean isProductIdExist = findProductService.isIdExist(getAllProducts, productId);

        if (isProductIdExist) {
            Product findProductAddToCart = findProductService.findById(getAllProducts, productId);
            errors = addProductToCart(findProductAddToCart, productToCartRequest.getQuantity());
        } else {
            errors.add(new CoreError("Id error ", "Wrong Id"));
        }

        if (errors.isEmpty()) {
            return new AddProductToCartResponse(productId);
        }
        return new AddProductToCartResponse(errors);
    }

    private List<CoreError> addProductToCart(Product product, int userQuantity) {
        ProductQuantityValidator quantityValidator = new ProductQuantityValidator();
        List<CoreError> errors = quantityValidator.validate(product.getQuantity(), userQuantity);
        if (errors.isEmpty()) {
            product.setQuantity(userQuantity);
            cart.addProductToCart(product);
        }
        return errors;
    }
}