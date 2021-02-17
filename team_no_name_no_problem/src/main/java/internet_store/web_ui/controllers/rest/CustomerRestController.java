package internet_store.web_ui.controllers.rest;

import internet_store.core.requests.customer.FindCustomerByIdRequest;
import internet_store.core.requests.product.FindProductByIdRequest;
import internet_store.core.response.customer.FindCustomerByIdResponse;
import internet_store.core.response.product.FindProductByIdResponse;
import internet_store.core.services.customer.FindCustomerByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
