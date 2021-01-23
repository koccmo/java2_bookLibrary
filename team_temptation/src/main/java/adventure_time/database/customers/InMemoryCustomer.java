package adventure_time.database.customers;

import adventure_time.core.domain.Customers;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class InMemoryCustomer implements DatabaseCustomers {

    private Long idCounter =1L;
    private final List<Customers> customers = new ArrayList<>();

    @Override
    public boolean add(Customers customer) {
        if (!customers.isEmpty()) {
            for (Customers item : customers) {
                if (item.getCustomerName().equals(customer.getCustomerName())) return false;
            }
        }
        customer.setCustomerID(idCounter);
        idCounter++;
        return customers.add(customer);
    }

    @Override
    public boolean activate(Long id) {
        for (Customers customer : customers) {
            if (customer.getCustomerID().equals(id)) {
                customer.setActivity(true);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deactivate(Long id) {
        for (Customers customer : customers) {
            if (customer.getCustomerID().equals(id)) {
                customer.setActivity(true);
                return true;
            }
        }
        return false;
    }

    public List<Customers> getCustomersList() {
        return customers;
    }

    @Override
    public Optional<Customers> findById(Long id) {
        return customers.stream().filter(items -> items.getCustomerID().equals(id)).findFirst();
    }

    @Override
    public Optional<Customers> findByName(String name) {
        return customers.stream().filter(items -> items.getCustomerName().equals(name)).findFirst();
    }

    @Override
    public boolean updateCustomer(Customers customer) {
        return false;
    }

    @Override
    public List<Customers> findAllActiveCustomers() {
        return customers.stream()
                .filter(Customers::getActivity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Customers> findAllInactiveCustomers() {
        return customers.stream()
                .filter(item -> !item.getActivity())
                .collect(Collectors.toList());
    }

}
