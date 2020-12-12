package dental_clinic.console_ui;

import dental_clinic.core.domain.OrderingDirection;
import dental_clinic.core.requests.Ordering;
import dental_clinic.core.requests.Paging;
import dental_clinic.core.requests.SearchPatientRequest;
import dental_clinic.core.responses.SearchPatientResponse;
import dental_clinic.core.services.SearchPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SearchPatientUIAction implements UIAction{

    @Autowired
    private SearchPatientService searchPatientService;

    @Override
    public void execute() {

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter name for search");
        String name = in.nextLine();

        System.out.println("Please enter surname for search");
        String surname = in.nextLine();

        System.out.println("Please enter orderBy: name/surname");
        String orderBy = in.nextLine();

        System.out.println("Please enter order direction: ASC/DESC");
        String orderDirection = in.nextLine();
        OrderingDirection orderingDirection = getOrderingDirection(orderDirection);

        System.out.println("Please enter page number");
        Integer pageNumber = in.nextInt();

        System.out.println("Please enter page size");
        Integer pageSize = in.nextInt();

        Ordering ordering = new Ordering(orderBy, orderingDirection);
        Paging paging = new Paging(pageNumber, pageSize);
        SearchPatientRequest searchPatientRequest =
                new SearchPatientRequest(name, surname, ordering, paging);
        SearchPatientResponse searchPatientResponse = searchPatientService.execute(searchPatientRequest);

        if (searchPatientResponse.hasErrors()){
            searchPatientResponse.getErrors().forEach(System.out::println);
        }else{
            searchPatientResponse.getPatients().forEach(System.out::println);
        }
    }

    private OrderingDirection getOrderingDirection (String orderDirection) {
    OrderingDirection orderingDirection;
        if (orderDirection.equalsIgnoreCase("ASC")){
            orderingDirection = OrderingDirection.ASC;
        }else {
            if (orderDirection.equalsIgnoreCase("DESC")) {
                orderingDirection = OrderingDirection.DESC;
            } else {
                orderingDirection = OrderingDirection.NOT_VALID;
            }
        }
        return orderingDirection;
    }
}
