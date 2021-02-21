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
    private SearchProductByIdService searchProductByIdService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public SearchProductByIdResponse searchProductById(@PathVariable Long id) {
        SearchProductByIdRequest request = new SearchProductByIdRequest(id);
        return searchProductByIdService.execute(request);
    }

    @Autowired
    private SearchProductByOtherService searchProductByTitle;

    @GetMapping(path = "/{title}", produces = "application/json")
    public SearchProductByOtherResponse searchProductByTitle(@PathVariable String title) {
        SearchProductByOtherRequest request = new SearchProductByOtherRequest(title,"",null,null,null,null);
        return searchProductByTitle.execute(request);
    }

    @Autowired
    private SearchProductByOtherService searchProductByDescription;

    @GetMapping(path = "/{description}", produces = "application/json")
    public SearchProductByOtherResponse searchProductByDescription(@PathVariable String description) {
        SearchProductByOtherRequest request = new SearchProductByOtherRequest("",description,null,null,null,null);
        return searchProductByDescription.execute(request);
    }

    @Autowired
    private SearchProductByOtherService searchProductByPriceRange;

    @GetMapping(path = "/{startPrice}/{enPrice}", produces = "application/json")
    public SearchProductByOtherResponse searchProductByPriceRange(@PathVariable Integer startPrice, Integer endPrice) {
        SearchProductByOtherRequest request = new SearchProductByOtherRequest("","",startPrice,endPrice,null,null);
        return searchProductByPriceRange.execute(request);
    }

    @Autowired
    private SearchProductByOtherService searchProductByTitleAndDescription;

    @GetMapping(path = "/{title}/{description}", produces = "application/json")
    public SearchProductByOtherResponse searchProductByTitleAndDescription(@PathVariable String title, String description) {
        SearchProductByOtherRequest request = new SearchProductByOtherRequest(title,description,null,null,null,null);
        return searchProductByPriceRange.execute(request);
    }

    @Autowired
    private SearchProductByOtherService searchProductByTitleAndPriceRange;

    @GetMapping(path = "/{title}/{startPrice}/{endPrice}", produces = "application/json")
    public SearchProductByOtherResponse searchProductByTitleAndPriceRange(@PathVariable String title, Integer startPrice, Integer endPrice) {
        SearchProductByOtherRequest request = new SearchProductByOtherRequest(title,"",startPrice,endPrice,null,null);
        return searchProductByPriceRange.execute(request);
    }

    @Autowired
    private SearchProductByOtherService searchProductByTitleAndDescriptionAndPriceRange;

    @GetMapping(path = "/{title}/{description}/{startPrice}/{endPrice}", produces = "application/json")
    public SearchProductByOtherResponse searchProductByTitleAndDescriptionAndPriceRange(@PathVariable String title, String description, Integer startPrice, Integer endPrice) {
        SearchProductByOtherRequest request = new SearchProductByOtherRequest(title,"",startPrice,endPrice,null,null);
        return searchProductByPriceRange.execute(request);
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

    @DeleteMapping(path = "/{title}", produces = "application/json")
    public DeleteProductByOtherResponse deleteProductByTitle(@PathVariable String title) {
        DeleteProductByOtherRequest request = new DeleteProductByOtherRequest(title,"",null,null);
        return deleteProductByTitle.execute(request);
    }

    @Autowired
    private DeleteProductByOtherService deleteProductByDescription;

    @DeleteMapping(path = "/{description}", produces = "application/json")
    public DeleteProductByOtherResponse deleteProductByDescription(@PathVariable String description) {
        DeleteProductByOtherRequest request = new DeleteProductByOtherRequest("",description,null,null);
        return deleteProductByDescription.execute(request);
    }

    @Autowired
    private DeleteProductByOtherService deleteProductByTitleAndDescription;

    @DeleteMapping(path = "/{title}/{description}", produces = "application/json")
    public DeleteProductByOtherResponse deleteProductByTitleAndDescription(@PathVariable String title, String description) {
        DeleteProductByOtherRequest request = new DeleteProductByOtherRequest(title,description,null,null);
        return deleteProductByTitleAndDescription.execute(request);
    }

    @Autowired
    private DeleteProductByOtherService deleteProductByTitleAnPriceRange;

    @DeleteMapping(path = "/{title}/{description}", produces = "application/json")
    public DeleteProductByOtherResponse deleteProductByTitleAndPriceRange(@PathVariable String title, Integer startPrice, Integer endPrice) {
        DeleteProductByOtherRequest request = new DeleteProductByOtherRequest(title,"",startPrice,endPrice);
        return deleteProductByTitleAnPriceRange.execute(request);
    }

    @Autowired
    private DeleteProductByOtherService deleteProductByDescriptionAnPriceRange;

    @DeleteMapping(path = "/{title}/{description}", produces = "application/json")
    public DeleteProductByOtherResponse deleteProductByDescriptionAndPriceRange(@PathVariable String description, Integer startPrice, Integer endPrice) {
        DeleteProductByOtherRequest request = new DeleteProductByOtherRequest("",description,startPrice,endPrice);
        return deleteProductByDescriptionAnPriceRange.execute(request);
    }

    @Autowired
    private DeleteProductByOtherService deleteProductByTitleAndDescriptionAndPriceRange;

    @DeleteMapping(path = "/{title}/{description}", produces = "application/json")
    public DeleteProductByOtherResponse deleteProductByTitleAndDescriptionAndPriceRange(@PathVariable String title, String description, Integer startPrice, Integer endPrice) {
        DeleteProductByOtherRequest request = new DeleteProductByOtherRequest(title,description,startPrice,endPrice);
        return deleteProductByTitleAndDescriptionAndPriceRange.execute(request);
    }
}

