package lv.estore.app.userInterface;

import lv.estore.app.core.domain.Product;
import lv.estore.app.core.request.Ordering;
import lv.estore.app.core.request.Paging;
import lv.estore.app.core.request.SearchRequest;
import lv.estore.app.core.responses.SearchResponse;
import lv.estore.app.core.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SearchUIAction implements UIAction{

    @Autowired
    SearchService searchService;

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

        SearchRequest request = new SearchRequest(name, price, ordering, paging);
        SearchResponse response = searchService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        } else {
            response.getProducts().forEach(Product::toString);
        }
        System.out.println("Press 'Enter' to continue.");
    }
}
