package internet_store.console_ui.product;

import internet_store.console_ui.UIAction;
import internet_store.core.domain.Customer;
import internet_store.core.domain.Order;
import internet_store.core.domain.Product;
import internet_store.core.requests.customer.AddCustomerRequest;
import internet_store.core.requests.product.BuyProductRequest;
import internet_store.core.response.customer.AddCustomerResponse;
import internet_store.core.response.product.BuyProductResponse;
import internet_store.core.services.customer.AddCustomerService;
import internet_store.core.services.product.BuyProductService;
import internet_store.database.order.OrderDatabase;
import internet_store.dependency_injection.DIDependency;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class BuyProductUIAction implements UIAction {

    @DIDependency private BuyProductService buyProductService;
    @DIDependency private AddCustomerService addCustomerService;
    @DIDependency private OrderDatabase orderDatabase;

    @Override
    public void execute() {

        Scanner in = new Scanner(System.in);

        Map<Product, Integer> shoppingCart = new HashMap<>();

        while (true) {
            System.out.println("To add product in Shopping Cart, please enter ID of this product: ");
            Long id = in.nextLong();

            System.out.println("Please enter quantity of this product");
            Integer quantity = in.nextInt();
            String endOfShopping = in.nextLine();

            System.out.println("Please enter CHECKOUT to make order");
            endOfShopping = in.nextLine();

            BuyProductRequest buyProductRequest = new BuyProductRequest(id, quantity, endOfShopping);
            BuyProductResponse buyProductResponse = buyProductService.execute(buyProductRequest);

            if (buyProductResponse.hasErrors()) {
                buyProductResponse.getErrors().forEach(System.out::println);
            }else {
                Product product = buyProductResponse.getProduct();
                shoppingCart.put(product, buyProductRequest.getQuantity());
            }

            if (endOfShopping.equalsIgnoreCase("CHECKOUT")) {
                Customer newCustomer = getNewCustomer();
                Order order = new Order(newCustomer, shoppingCart);
                orderDatabase.addOrder(order);
                System.out.println("Thank you for the order!\n");
                System.out.println(order.toString());
                break;
            }
        }
    }

    private Customer getNewCustomer () {

        Customer newCustomer;

        while (true) {
            Scanner in = new Scanner(System.in);

            System.out.println("Please enter customer's name: ");
            String name = in.nextLine();

            System.out.println("Please enter customer's surname: ");
            String surname = in.nextLine();

            System.out.println("Please enter customer's phone number: ");
            String phoneNumber = in.nextLine();

            System.out.println("Please enter customer's address: ");
            String address = in.nextLine();

            System.out.println("Please enter customer's e-mail: ");
            String email = in.nextLine();

            newCustomer = new Customer(name, surname, phoneNumber, address, email);

            AddCustomerRequest addCustomerRequest = new AddCustomerRequest(newCustomer);
            AddCustomerResponse addCustomerResponse = addCustomerService.execute(addCustomerRequest);
            if (!addCustomerResponse.hasErrors()) {
                break;
            }else{
                addCustomerResponse.getErrors().forEach(System.out::println);
            }
        }
        return newCustomer;
    }

}
