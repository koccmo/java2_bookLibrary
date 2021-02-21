//package adventure_time.core.database.customers;
//
//import adventure_time.core.domain.Customers;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
////@Component
//public class InMemoryCustomer implements CustomerRepository {
//
//    private Long idCounter =1L;
//    private final List<Customers> customers = new ArrayList<>();
//
//    @Override
//    public boolean add(Customers customer) {
//        if (!customers.isEmpty()) {
//            for (Customers item : customers) {
//                if (item.getCustomerEmail().equals(customer.getCustomerEmail())) return false;
//            }
//        }
//        customer.setCustomerID(idCounter++);
//        return customers.add(customer);
//    }
//
//    @Override
//    public boolean activate(Long id) {
//        for (Customers customer : customers) {
//            if (customer.getCustomerID().equals(id)) {
//                int index = customers.indexOf(customer);
//                customer.setActivity(true);
//                customers.set(index, customer);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public boolean deactivate(Long id) {
//        for (Customers customer : customers) {
//            if (customer.getCustomerID().equals(id)) {
//                int index = customers.indexOf(customer);
//                customer.setActivity(false);
//                customers.set(index, customer);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public Optional<Customers> findById(Long id) {
//        return customers.stream().filter(items -> items.getCustomerID().equals(id)).findFirst();
//    }
//
//    @Override
//    public Optional<Customers> findByEmail(String email) {
//        return customers.stream().filter(items -> items.getCustomerEmail().equals(email)).findFirst();
//    }
//
//    @Override
//    public boolean updateCustomer(Customers customer, Long id) {
//        for (Customers item : customers) {
//            if (item.getCustomerEmail().equals(customer.getCustomerEmail()) && !item.getCustomerID().equals(id)) return false;
//        }
//        Customers customerOld = findById(id).get();
//        int index = customers.indexOf(customerOld);
//        customer.setCustomerID(id);
//        customers.set(index, customer);
//        return true;
//    }
//
//    @Override
//    public List<Customers> findAllActiveCustomers() {
//        return customers.stream()
//                .filter(Customers::getActivity)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<Customers> findAllInactiveCustomers() {
//        return customers.stream()
//                .filter(item -> !item.getActivity())
//                .collect(Collectors.toList());
//    }
//}
