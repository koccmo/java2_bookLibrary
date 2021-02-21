package internet_store.web_ui.controllers;

import internet_store.core.requests.product.BuyProductRequest;
import internet_store.core.response.product.BuyProductResponse;
import internet_store.core.services.product.BuyProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BuyProductController {

        @Autowired
        private BuyProductService buyProductService;

        @GetMapping(value = "/buyProduct")
        public String showBuyProductPage(ModelMap modelMap) {
            modelMap.addAttribute("request", new BuyProductRequest());
            return "buyProduct";
        }

        @PostMapping("/buyProduct")
        public String processBuyProductRequest(@ModelAttribute(value = "request") BuyProductRequest request, ModelMap modelMap) {
            BuyProductResponse buyProductResponse = buyProductService.execute(request);
            if (buyProductResponse.hasErrors()) {
                modelMap.addAttribute("errors", buyProductResponse.getErrors());
                return "/buyProduct";
            } else {
                return "index";
            }
        }
    }

