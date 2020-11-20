package internet_store.console_ui.product;

import internet_store.console_ui.UIAction;
import internet_store.core.requests.customer.SearchProductRequest;
import internet_store.core.response.product.SearchProductResponse;
import internet_store.core.services.product.SearchProductService;

import java.util.Scanner;

public class SearchProductUIAction implements UIAction {

    private final SearchProductService searchProductService;

    public SearchProductUIAction(SearchProductService searchProductService) {
        this.searchProductService = searchProductService;
    }

    public void execute (){

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter title to search by title");
        String title = in.nextLine();

        System.out.println("Please enter description to search by description");
        String description = in.nextLine();

        SearchProductRequest searchProductRequest = new SearchProductRequest(title, description);
        SearchProductResponse searchProductResponse = searchProductService.execute(searchProductRequest);

        if (searchProductResponse.hasErrors()){
            searchProductResponse.getErrors().forEach(System.out::println);
        }else{
            searchProductResponse.getProducts().forEach(System.out::println);
        }

    }
}
