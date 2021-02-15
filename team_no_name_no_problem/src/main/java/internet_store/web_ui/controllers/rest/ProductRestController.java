package internet_store.web_ui.controllers.rest;

import internet_store.core.requests.product.AddProductRequest;
import internet_store.core.requests.product.FindProductByIdRequest;
import internet_store.core.response.product.AddProductResponse;
import internet_store.core.response.product.FindProductByIdResponse;
import internet_store.core.services.product.AddProductService;
import internet_store.core.services.product.FindProductByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    @Autowired
    private FindProductByIdService findProductByIdService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public FindProductByIdResponse findProductById(@PathVariable Long id) {
        FindProductByIdRequest request = new FindProductByIdRequest(id);
        return findProductByIdService.execute(request);
    }

    @Autowired
    private AddProductService addProductService;

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public AddProductResponse addProduct(@RequestBody AddProductRequest request) {
        return addProductService.execute(request);
    }
}

