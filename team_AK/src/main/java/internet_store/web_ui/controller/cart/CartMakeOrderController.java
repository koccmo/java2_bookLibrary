package internet_store.web_ui.controller.cart;

import internet_store.core.domain.ProductInCart;
import internet_store.core.domain.Client;
import internet_store.core.domain.Order;
import internet_store.core.operation.Tax;
import internet_store.core.service.cart.CartProductsCountService;
import internet_store.core.service.client.AddClientService;
import internet_store.core.service.ordering.CreateOrderNumberService;
import internet_store.core.service.ordering.OrderService;
import internet_store.core.persistence.ClientRepository;
import internet_store.core.service.ordering.OrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class CartMakeOrderController {
    @Autowired
    private OrderStatusService orderStatusService;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddClientService addClientService;
    @Autowired
    private CartProductsCountService cartCountService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CreateOrderNumberService numberService;
    @Autowired
    private Tax tax;
    private long cartCount;
    private List<ProductInCart> items;

    @GetMapping(value = "cart_make_order")
    public String getCartOrder(ModelMap modelMap) {
        Client client = orderService.getClient();
        modelMap.addAttribute("client", Objects.requireNonNullElseGet(client, Client::new));

        Order order = orderService.createOrder();
        updatePage();

        if (items.size() == 0) {
            modelMap.addAttribute("order", new Order());
        } else {
            modelMap.addAttribute("order", order);
            modelMap.addAttribute("items", items);
        }

        modelMap.addAttribute("cartCount", cartCount);
        modelMap.addAttribute("info", "");
        modelMap.addAttribute("orderNumber", numberService.getFullOrderNumber());
        modelMap.addAttribute("currency_symbol", tax.getCurrencySymbol());

        if (orderService.isCanMakeOrder()) {
            modelMap.addAttribute("disabled", "false");
        } else {
            modelMap.addAttribute("disabled", "true");
        }
        return "cart/cart_make_order";
    }

    @PostMapping(value = "/make_order")
    public String makeOrder(@ModelAttribute(value = "client") Client client, ModelMap modelMap) {

        orderService.saveOrder();
        orderStatusService.changeOrderStatus(numberService.getFullOrderNumber(), "ORDER RECEIVED");

        updatePage();
        numberService.setOrderHaveNumber(false);
        orderService.setClient(new Client());
        modelMap.addAttribute("order", new Order());
        modelMap.addAttribute("items", new ArrayList<>());
        modelMap.addAttribute("client", new Client());
        modelMap.addAttribute("cartCount", cartCount);
        modelMap.addAttribute("info", "");
        modelMap.addAttribute("disabled", "true");
        modelMap.addAttribute("orderNumber", numberService.getFullOrderNumber());
        modelMap.addAttribute("currency_symbol", tax.getCurrencySymbol());
        return "cart/cart_make_order";
    }

    private void updatePage() {
        cartCount = cartCountService.getCartCount();
        items = orderService.getAllItemsFromCart();
    }
}