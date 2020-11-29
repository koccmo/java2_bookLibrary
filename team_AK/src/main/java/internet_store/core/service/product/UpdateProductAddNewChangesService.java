package internet_store.core.service.product;

import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Product;
import internet_store.database.product_database.InnerProductDatabase;

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
            int updateIndex = productDatabase.findProductIndex(product.getId());
            productDatabase.updateProduct(updateIndex, product);
        }
    }
}