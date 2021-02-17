package internet_store.application.web_ui.controllers.rest;

import internet_store.application.core.requests.product.AddProductRequest;
import internet_store.application.core.requests.product.DeleteProductRequest;
import internet_store.application.core.requests.product.FindByIdRequest;
import internet_store.application.core.requests.product.UpdateProductRequest;
import internet_store.application.core.responses.product.AddProductResponse;
import internet_store.application.core.responses.product.DeleteProductResponse;
import internet_store.application.core.responses.product.FindByProductIdResponse;
import internet_store.application.core.responses.product.UpdateProductResponse;
import internet_store.application.core.services.product.AddProductService;
import internet_store.application.core.services.product.DeleteProductService;
import internet_store.application.core.services.product.FindByProductIdService;
import internet_store.application.core.services.product.UpdateProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    @Autowired private FindByProductIdService findByProductIdService;
    @Autowired private AddProductService addProductService;
    @Autowired private UpdateProductService updateProductService;
    @Autowired private DeleteProductService deleteProductService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public FindByProductIdResponse getProduct(@PathVariable Long id) {
        FindByIdRequest request = new FindByIdRequest(String.valueOf(id));
        return findByProductIdService.execute(request);
    }

    @PostMapping(path = "/",
            produces = "application/json",
            consumes = "application/json")
    public AddProductResponse addProduct(@RequestBody AddProductRequest request){
        return  addProductService.execute(request);
    }

    @PutMapping(path = "/",
            produces = "application/json",
            consumes = "application/json")
    public UpdateProductResponse updateProduct(@RequestBody UpdateProductRequest request){
        return updateProductService.execute(request);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public DeleteProductResponse deleteProduct(@PathVariable Long id){
        DeleteProductRequest request = new DeleteProductRequest(id);
        return deleteProductService.execute(request);
    }

}
