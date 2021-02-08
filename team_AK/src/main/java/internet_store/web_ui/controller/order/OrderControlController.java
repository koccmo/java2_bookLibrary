package internet_store.web_ui.controller.order;

import internet_store.core.operation.Tax;
import internet_store.core.service.ordering.OrderStatusService;
import internet_store.core.service.ordering.paging.OrderControlPagingService;
import internet_store.core.persistence.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderControlController {
    @Autowired
    private CartRepository CartRepository;
    @Autowired
    private OrderControlPagingService paging;
    @Autowired
    private Tax tax;
    @Autowired
    private OrderStatusService orderStatusService;

    @GetMapping(value = "order_control")
    public String getOrders(ModelMap modelMap) {
        paging.startPaging();

        modelMap.addAttribute("info", "");
        modelMap.addAttribute("orders", paging.getListOnePage());
        modelMap.addAttribute("currency_symbol", tax.getCurrencySymbol());
        modelMap.addAttribute("pages", "Page " + paging.getCurrentPage() + " of "
                + paging.getPagesQuantity());
        return "order/order_control";
    }

    @PostMapping(value = "/order_control_next")
    public String nextPageOrderControl(ModelMap modelMap) {
        if (paging.isLastPage()) {
            modelMap.addAttribute("info", "View control : Last page");
        } else {
            modelMap.addAttribute("info", "");
            paging.nextPage(true);
        }

        modelMap.addAttribute("pages", "Page " + paging.getCurrentPage() + " of "
                + paging.getPagesQuantity());
        modelMap.addAttribute("orders", paging.getListOnePage());
        modelMap.addAttribute("currency_symbol", tax.getCurrencySymbol());
        return "order/order_control";
    }

    @PostMapping(value = "/order_control_prev")
    public String prevPageOrderControl(ModelMap modelMap) {
        if (paging.isFirstPage()) {
            modelMap.addAttribute("info", "View control : First page");
        } else {
            modelMap.addAttribute("info", "");
            paging.nextPage(false);
        }

        modelMap.addAttribute("pages", "Page " + paging.getCurrentPage() + " of "
                + paging.getPagesQuantity());
        modelMap.addAttribute("orders", paging.getListOnePage());
        modelMap.addAttribute("currency_symbol", tax.getCurrencySymbol());

        return "order/order_control";
    }

    @PostMapping(value = "/status_change")
    public String statusChange(@RequestParam(value = "orderNumber") String orderNumber,
                               @RequestParam(value = "order_status") String status,
                               ModelMap modelMap) {

        orderStatusService.changeOrderStatus(orderNumber, status);

        modelMap.addAttribute("info", "");
        modelMap.addAttribute("pages", "Page " + paging.getCurrentPage() + " of "
                + paging.getPagesQuantity());
        modelMap.addAttribute("orders", paging.refreshTableContent());
        modelMap.addAttribute("currency_symbol", tax.getCurrencySymbol());
        return "order/order_control";
    }
}