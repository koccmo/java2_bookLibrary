package userInterface;

import core.request.SearchRequest;
import core.services.iService;

import java.util.Scanner;

public class SearchUIAction implements UIAction{

    private iService searchService;

    public SearchUIAction(final iService searchService) {
        this.searchService = searchService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type product name: ");
        String name = scanner.nextLine();
        System.out.println("Type price: ");
        String price = scanner.nextLine();

        SearchRequest searchRequest = new SearchRequest(name, price);
        searchService.execute(searchRequest);
        System.out.println("Type 'Enter' to continue.");
    }
}
