package internet_store.application.web_ui.controllers.rest;

import internet_store.application.core.requests.order.*;
import internet_store.application.core.responses.order.*;
import internet_store.application.core.services.order.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderRestController {

    @Autowired
    private AddOrderService addOrderService;
    @Autowired
    private FindAllOrdersService findAllOrdersService;
    @Autowired
    private FindOrderByIdService findOrderByIdService;

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public AddOrderResponse addOrder(@RequestBody AddOrderRequest request) {
        return addOrderService.execute(request);
    }

    @GetMapping(path = "/", produces = "application/json")
    public FindAllOrdersResponse findAllOrders() {
        return findAllOrdersService.execute();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public FindOrderByIdResponse findOrderById(@PathVariable Long id) {
        FindOrderByIdRequest request = new FindOrderByIdRequest(id);
        return findOrderByIdService.execute(request);
    }

}
