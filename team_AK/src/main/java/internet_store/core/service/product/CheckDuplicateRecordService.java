package internet_store.core.service.product;

import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Product;
import internet_store.core.request.product.CheckDuplicateRecordRequest;
import internet_store.core.response.product.CheckDuplicateRecordResponse;
import internet_store.database.product_database.ProductDatabaseImpl;
import internet_store.persistence.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class CheckDuplicateRecordService {

    public CheckDuplicateRecordResponse execute(CheckDuplicateRecordRequest request, Product product) {
        Object databases = request.getProductDatabase();
        ProductDatabaseImpl innerDatabase;
        ProductRepository productRepository;
        boolean isDuplicate = true;

        if (databases instanceof ProductDatabaseImpl) {
            innerDatabase = (ProductDatabaseImpl) databases;
            isDuplicate = (innerDatabase.findByTitle(product.getTitle()) != null);
        }

        if (databases instanceof ProductRepository) {
            productRepository = (ProductRepository) databases;
            isDuplicate = (productRepository.findByTitle(product.getTitle()) != null);
        }

        if (isDuplicate) {
            List<CoreError> errors = new ArrayList<>();
            errors.add(new CoreError("Add command error: ", "Record exist in database"));
            return new CheckDuplicateRecordResponse(errors);
        }
        return new CheckDuplicateRecordResponse(new ArrayList<>());
    }
}