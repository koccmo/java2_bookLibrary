package lv.estore.app.console_ui.products;

import lv.estore.app.core.domain.Product;
import lv.estore.app.core.request.products.Ordering;
import lv.estore.app.core.request.products.Paging;
import lv.estore.app.core.request.products.SearchProductByNamePriceRequest;
import lv.estore.app.core.responses.products.SearchProductResponse;
import lv.estore.app.core.services.products.SearchProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SearchUIAction implements UIAction{

    @Autowired
    SearchProductsService searchProductsService;

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type product name: ");
        String name = scanner.nextLine();
        System.out.println("Type product price: ");
        String price = scanner.nextLine();

        //Ordering
        System.out.println("Type orderBy (name||price): ");
        String orderBy = scanner.nextLine();
        System.out.println("Type direction (ASCENDING||DESCENDING): ");
        String direction = scanner.nextLine();
        Ordering ordering = new Ordering(orderBy, direction);

        //Paging
        System.out.println("Enter page number: ");
        Integer pageNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter page size: ");
        Integer pageSize = Integer.parseInt(scanner.nextLine());
        Paging paging = new Paging(pageNumber, pageSize);

        SearchProductByNamePriceRequest request = new SearchProductByNamePriceRequest(name, price, ordering, paging);
        SearchProductResponse response = searchProductsService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            response.getProducts().forEach(Product::toString);
        }
        System.out.println("Press 'Enter' to continue.");
    }
}
