package lesson_3.core.service.add_product;

import lesson_3.ProductListApplication;
import lesson_3.core.core_error.CoreError;
import lesson_3.core.domain.Product;
import lesson_3.database.product_database.InnerProductDatabase;

import java.util.List;

public class UpdateProductAddNewChangesService extends AddProductService implements ProductUpdate {
    private final InnerProductDatabase productDatabase;

    public UpdateProductAddNewChangesService(InnerProductDatabase productDatabase) {
        super(productDatabase);
        this.productDatabase = productDatabase;
    }

    @Override
    public void execute(List<CoreError> errors, Product product) {
        if (errors.isEmpty()) {
            int updateIndex = ProductListApplication.findProductService.findProductIndex(productDatabase.getAllProducts(),
                    product.getId());
            productDatabase.updateProduct(updateIndex, product);
        }
    }
}