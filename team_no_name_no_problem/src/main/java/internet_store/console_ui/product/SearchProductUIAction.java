package internet_store.console_ui.product;

import internet_store.console_ui.UIAction;
import internet_store.core.requests.Ordering;
import internet_store.core.requests.Paging;
import internet_store.core.requests.product.SearchProductRequest;
import internet_store.core.response.product.SearchProductResponse;
import internet_store.core.services.product.SearchProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component public class SearchProductUIAction implements UIAction {

    @Autowired private SearchProductService searchProductService;

    @Override
    public void execute (){

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter title to search by title");
        String title = in.nextLine();

        System.out.println("Please enter description to search by description");
        String description = in.nextLine();

        System.out.println("Please enter price to search by price");
        int price = in.nextInt();

        System.out.println("Please enter order by: title / description / price");
        String orderBy = in.nextLine();

        System.out.println("Please enter order direction");
        String orderDirection = in.nextLine();

        System.out.println("Please enter page number");
        Integer pageNumber = in.nextInt();

        System.out.println("Please enter page size");
        Integer pageSize = in.nextInt();

        Ordering ordering = new Ordering(orderBy, orderDirection);
        Paging paging = new Paging(pageNumber, pageSize);
        SearchProductRequest searchProductRequest = new SearchProductRequest(title, description, price, ordering, paging);
        SearchProductResponse searchProductResponse = searchProductService.execute(searchProductRequest);

        if (searchProductResponse.hasErrors()){
            searchProductResponse.getErrors().forEach(System.out::println);
        }else{
            searchProductResponse.getProducts().forEach(System.out::println);
        }

    }
}
