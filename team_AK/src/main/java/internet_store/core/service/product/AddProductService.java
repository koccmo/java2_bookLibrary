package internet_store.core.service.product;

import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Product;
import internet_store.core.request.product.AddProductRequest;
import internet_store.core.request.product.CheckDuplicateRecordRequest;
import internet_store.core.request.product.product_items.AddProductDescriptionRequest;
import internet_store.core.request.product.product_items.AddProductPriceRequest;
import internet_store.core.request.product.product_items.AddProductQuantityRequest;
import internet_store.core.request.product.product_items.AddProductTitleRequest;
import internet_store.core.response.product.AddProductResponse;
import internet_store.core.response.product.CheckDuplicateRecordResponse;
import internet_store.core.response.product.product_item.AddProductDescriptionResponse;
import internet_store.core.response.product.product_item.AddProductPriceResponse;
import internet_store.core.response.product.product_item.AddProductQuantityResponse;
import internet_store.core.response.product.product_item.AddProductTitleResponse;
import internet_store.database.product_database.ProductDatabaseImpl;
import internet_store.persistence.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddProductService {
    private final AddProductTitleService titleService = new AddProductTitleService();
    private final AddProductDescriptionService descriptionService = new AddProductDescriptionService();
    private final AddProductQuantityService quantityService = new AddProductQuantityService();
    private final AddProductPriceService priceService = new AddProductPriceService();
    private final CheckDuplicateRecordService duplicateRecordService = new CheckDuplicateRecordService();

    public AddProductResponse execute(AddProductRequest request) {
        List<CoreError> errors = new ArrayList<>();

        AddProductTitleResponse titleResponse = titleService.execute(new AddProductTitleRequest
                (request.getProduct().getTitle()));
        AddProductDescriptionResponse descriptionResponse = descriptionService.execute(new AddProductDescriptionRequest
                (request.getProduct().getDescription()));
        AddProductQuantityResponse quantityResponse = quantityService.execute(new AddProductQuantityRequest
                (request.getProduct().getQuantity()));
        AddProductPriceResponse priceResponse = priceService.execute(new AddProductPriceRequest
                (request.getProduct().getPrice()));
        CheckDuplicateRecordResponse duplicateResponse = duplicateRecordService.execute(new
                CheckDuplicateRecordRequest(request.getProductDatabase()), request.getProduct());

        if (titleResponse.hasErrors()) {
            errors.add(new CoreError("Title input error: ", titleResponse.getErrors().get(0).getMessage()));
        }
        if (descriptionResponse.hasErrors()) {
            errors.add(new CoreError("Description input error: ", descriptionResponse.getErrors().get(0).getMessage()));
        }
        if (quantityResponse.hasErrors()) {
            errors.add(new CoreError("Quantity input error: ", quantityResponse.getErrors().get(0).getMessage()));
        }
        if (priceResponse.hasErrors()) {
            errors.add(new CoreError("Price input error: ", priceResponse.getErrors().get(0).getMessage()));
        }

        if (duplicateResponse.hasErrors()) {
            errors.add(new CoreError("Add command error: ", duplicateResponse.getErrors().get(0).getMessage()));
        }

        execute(errors, request);

        return new AddProductResponse(errors);
    }

    private void execute(List<CoreError> errors, AddProductRequest request) {
        Product product = request.getProduct();
        Object databases = request.getProductDatabase();
        ProductDatabaseImpl innerDatabase;
        ProductRepository productRepository;

        if (databases instanceof ProductDatabaseImpl) {
            innerDatabase = (ProductDatabaseImpl) databases;
            if (errors.isEmpty()) {
                innerDatabase.addProduct(product);
            }
        }

        if (databases instanceof ProductRepository) {
            productRepository = (ProductRepository) databases;
            if (errors.isEmpty()) {
                productRepository.save(product);
            }
        }
    }
}