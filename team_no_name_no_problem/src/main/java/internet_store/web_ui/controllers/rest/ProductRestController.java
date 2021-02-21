package internet_store.web_ui.controllers.rest;

import internet_store.core.requests.product.*;
import internet_store.core.response.product.*;
import internet_store.core.services.product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    @Autowired
    private FindProductByIdService findProductByIdService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public SearchProductByIdResponse searchProductById(@PathVariable Long id) {
        SearchProductByIdRequest request = new SearchProductByIdRequest(id);
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

    @Autowired
    private ChangeProductService changeProductService;

    @PutMapping(path = "/{id}",
            consumes = "application/json",
            produces = "application/json")
    public ChangeProductResponse changeProduct(@RequestBody ChangeProductRequest request) {
        return changeProductService.execute(request);
    }

    @Autowired
    private DeleteProductByIdService deleteProductByIdService;

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public DeleteProductByIdResponse deleteProduct(@PathVariable Long id) {
        DeleteProductByIdRequest request = new DeleteProductByIdRequest(id);
        return deleteProductByIdService.execute(request);
    }

    @Autowired
    private DeleteProductByOtherService deleteProductByTitle;

    @DeleteMapping(path = "/title", produces = "application/json")
    public DeleteProductByOtherResponse deleteProductByTitle(@PathVariable String title) {
        DeleteProductByOtherRequest request = new DeleteProductByOtherRequest(title,"",null,null);
        return deleteProductByTitle.execute(request);
    }

    @Autowired
    private DeleteProductByOtherService deleteProductByDescription;

    @DeleteMapping(path = "/description", produces = "application/json")
    public DeleteProductByOtherResponse deleteProductByDescription(@PathVariable String description) {
        DeleteProductByOtherRequest request = new DeleteProductByOtherRequest("",description,null,null);
        return deleteProductByDescription.execute(request);
    }
}

