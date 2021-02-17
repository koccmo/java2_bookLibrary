package internet_store.web_ui.controllers.rest;

import internet_store.core.requests.customer.AddCustomerRequest;
import internet_store.core.requests.customer.FindCustomerByIdRequest;
import internet_store.core.requests.product.AddProductRequest;
import internet_store.core.response.customer.AddCustomerResponse;
import internet_store.core.response.customer.FindCustomerByIdResponse;
import internet_store.core.response.product.AddProductResponse;
import internet_store.core.services.customer.AddCustomerService;
import internet_store.core.services.customer.FindCustomerByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {

    @Autowired
    private FindCustomerByIdService findCustomerByIdService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public FindCustomerByIdResponse findCustomerById(@PathVariable Long id) {
        FindCustomerByIdRequest request = new FindCustomerByIdRequest(id);
        return findCustomerByIdService.execute(request);
    }

    @Autowired
    private AddCustomerService addCustomerService;

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public AddCustomerResponse addCustomer(@RequestBody AddCustomerRequest request) {
        return addCustomerService.execute(request);
    }
}
