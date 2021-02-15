package internet_store.web_ui.controllers.rest;

import internet_store.core.requests.product.FindProductByIdRequest;
import internet_store.core.response.product.FindProductByIdResponse;
import internet_store.core.services.product.FindProductByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    @Autowired private FindProductByIdService findProductByIdService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public FindProductByIdResponse findProductById(@PathVariable Long id) {
        FindProductByIdRequest request = new FindProductByIdRequest(id);
        return findProductByIdService.execute(request);
    }

}
