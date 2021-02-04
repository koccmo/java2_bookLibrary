package internet_store.controller.cart;

import internet_store.core.core_error.CoreError;
import internet_store.core.domain.Client;
import internet_store.core.request.client.AddClientRequest;
import internet_store.core.response.client.AddClientResponse;
import internet_store.core.service.cart.CartProductsCountService;
import internet_store.core.service.client.AddClientService;
import internet_store.core.service.ordering.OrderService;
import internet_store.persistence.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartAddClientController {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    AddClientService addClientService;
    @Autowired
    CartProductsCountService cartCountService;
    @Autowired
    OrderService orderService;
    private long cartCount;

    @GetMapping(value = "cart_add_client")
    public String getCartClient(ModelMap modelMap) {
        updatePage();
        modelMap.addAttribute("client", new Client());
        modelMap.addAttribute("cartCount", cartCount);
        modelMap.addAttribute("errors", "");
        modelMap.addAttribute("info", "");
        modelMap.addAttribute("disabled", "false");
        return "cart/cart_add_client";
    }

    @PostMapping(value = "/order_add_client")
    public String addNewClient(@ModelAttribute(value = "client") Client client, ModelMap modelMap) {
        StringBuilder allErrors = new StringBuilder();

        updatePage();

        AddClientRequest request = new AddClientRequest(client, clientRepository);
        AddClientResponse response = addClientService.execute(request);

        for (CoreError error : response.getErrors()) {
            allErrors.append(error.getField()).append(" ").append(error.getMessage()).append(" ");
        }
        if (!(response.hasErrors())) {
            orderService.setClient(client);
            modelMap.addAttribute("info", "Client data successful created");
        } else {
            modelMap.addAttribute("info", "");
        }

        modelMap.addAttribute("cartCount", cartCount);
        modelMap.addAttribute("errors", allErrors.toString());
        modelMap.addAttribute("client", client);
        return "cart/cart_add_client";
    }

    private void updatePage() {
        cartCount = cartCountService.getCartCount();
    }
}