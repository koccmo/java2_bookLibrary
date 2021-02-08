package internet_store.web_ui.controller;

import internet_store.core.operation.Tax;
import internet_store.core.service.client.paging.ClientPagingService;
import internet_store.core.service.ordering.CreateOrderNumberService;
import internet_store.core.service.ordering.paging.OrderControlPagingService;
import internet_store.core.service.product.paging.ProductsPagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller()
public class ServiceController {
    @Autowired
    ProductsPagingService productsPagingService;
    @Autowired
    ClientPagingService clientPagingService;
    @Autowired
    OrderControlPagingService orderPagingService;
    @Autowired
    CreateOrderNumberService numberService;
    @Autowired
    Tax tax;


    @GetMapping(value = "service")
    public String service(ModelMap modelMap) {
        return "service/service";
    }

    @GetMapping(value = "general_settings")
    public String entry(ModelMap modelMap) {
        modelMap.addAttribute("product_page_quantity", productsPagingService.getRecordsCountOnPage());
        modelMap.addAttribute("client_page_quantity", clientPagingService.getRecordsCountOnPage());
        modelMap.addAttribute("order_page_quantity", orderPagingService.getRecordsCountOnPage());
        modelMap.addAttribute("tax", tax.getTaxRate());
        modelMap.addAttribute("currency", tax.getCurrencySymbol());
        modelMap.addAttribute("orderNumber", numberService.getOrderNumber());
        return "service/general_service";
    }

    @PostMapping(value = "set_product_on_page")
    public String setProductOnPage(@RequestParam("product_on_page") Integer productOnPage,
                                   ModelMap modelMap) {
        productsPagingService.setRecordsCountOnPage(productOnPage);
        modelMap.addAttribute("product_page_quantity", productsPagingService.getRecordsCountOnPage());
        modelMap.addAttribute("client_page_quantity", clientPagingService.getRecordsCountOnPage());
        modelMap.addAttribute("order_page_quantity", orderPagingService.getRecordsCountOnPage());
        modelMap.addAttribute("tax", tax.getTaxRate());
        modelMap.addAttribute("currency", tax.getCurrencySymbol());
        modelMap.addAttribute("orderNumber", numberService.getOrderNumber());
        return "service/general_service";
    }

    @PostMapping(value = "set_client_on_page")
    public String setClientOnPage(@RequestParam("client_on_page") Integer clientOnPage,
                                  ModelMap modelMap) {
        clientPagingService.setRecordsCountOnPage(clientOnPage);
        modelMap.addAttribute("product_page_quantity", productsPagingService.getRecordsCountOnPage());
        modelMap.addAttribute("client_page_quantity", clientPagingService.getRecordsCountOnPage());
        modelMap.addAttribute("order_page_quantity", orderPagingService.getRecordsCountOnPage());
        modelMap.addAttribute("tax", tax.getTaxRate());
        modelMap.addAttribute("currency", tax.getCurrencySymbol());
        modelMap.addAttribute("orderNumber", numberService.getOrderNumber());
        return "service/general_service";
    }

    @PostMapping(value = "set_order_on_page")
    public String setOrderOnPage(@RequestParam("order_on_page") Integer orderOnPage,
                                  ModelMap modelMap) {
        orderPagingService.setRecordsCountOnPage(orderOnPage);
        modelMap.addAttribute("product_page_quantity", productsPagingService.getRecordsCountOnPage());
        modelMap.addAttribute("client_page_quantity", clientPagingService.getRecordsCountOnPage());
        modelMap.addAttribute("order_page_quantity", orderPagingService.getRecordsCountOnPage());
        modelMap.addAttribute("tax", tax.getTaxRate());
        modelMap.addAttribute("currency", tax.getCurrencySymbol());
        modelMap.addAttribute("orderNumber", numberService.getOrderNumber());
        return "service/general_service";
    }

    @PostMapping(value = "tax")
    public String setTax(@RequestParam("tax_rate") BigDecimal taxRate,
                         ModelMap modelMap) {
        tax.setTaxRate(taxRate);
        modelMap.addAttribute("product_page_quantity", productsPagingService.getRecordsCountOnPage());
        modelMap.addAttribute("client_page_quantity", clientPagingService.getRecordsCountOnPage());
        modelMap.addAttribute("order_page_quantity", orderPagingService.getRecordsCountOnPage());
        modelMap.addAttribute("tax", tax.getTaxRate());
        modelMap.addAttribute("currency", tax.getCurrencySymbol());
        modelMap.addAttribute("orderNumber", numberService.getOrderNumber());
        return "service/general_service";
    }

    @PostMapping(value = "currency")
    public String setCurrency(@RequestParam("currency") String currency,
                              ModelMap modelMap) {

        tax.setCurrencySymbol(currency);
        modelMap.addAttribute("product_page_quantity", productsPagingService.getRecordsCountOnPage());
        modelMap.addAttribute("client_page_quantity", clientPagingService.getRecordsCountOnPage());
        modelMap.addAttribute("order_page_quantity", orderPagingService.getRecordsCountOnPage());
        modelMap.addAttribute("tax", tax.getTaxRate());
        modelMap.addAttribute("currency", tax.getCurrencySymbol());
        modelMap.addAttribute("orderNumber", numberService.getOrderNumber());
        return "service/general_service";
    }

    @PostMapping(value = "order_number")
    public String setOrderNumber(@RequestParam("orderNumber") Integer orderNumber,
                                  ModelMap modelMap) {
        numberService.setOrderNumber(orderNumber);
        modelMap.addAttribute("product_page_quantity", productsPagingService.getRecordsCountOnPage());
        modelMap.addAttribute("client_page_quantity", clientPagingService.getRecordsCountOnPage());
        modelMap.addAttribute("order_page_quantity", orderPagingService.getRecordsCountOnPage());
        modelMap.addAttribute("tax", tax.getTaxRate());
        modelMap.addAttribute("currency", tax.getCurrencySymbol());
        modelMap.addAttribute("orderNumber", numberService.getOrderNumber());
        return "service/general_service";
    }
}