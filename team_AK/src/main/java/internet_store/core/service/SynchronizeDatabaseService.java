package internet_store.core.service;

import internet_store.core.domain.Client;
import internet_store.core.domain.Product;
import internet_store.database.client_database.ClientDatabaseImpl;
import internet_store.database.product_database.ProductDatabaseImpl;
import internet_store.persistence.ClientRepository;
import internet_store.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SynchronizeDatabaseService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductDatabaseImpl productDatabase;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ClientDatabaseImpl clientDatabase;

    public void synchronize() {
        List<Client> allClients = clientRepository.findAll();
        List<Product> allProducts = productRepository.findAll();

        System.out.println("Synchronize starting........");
        System.out.println("Found " + clientRepository.count() + " records in MySql database table [Client]");
        System.out.println("Found " + productRepository.count() + " records in MySql database table [Product]");

        clientDatabase.addAll(allClients);
        productDatabase.addAll(allProducts);

        System.out.println("Copy " + clientDatabase.recordsCount() + " records to Memory database table [Client]");
        System.out.println("Copy " + productDatabase.recordsCount() + " records to Memory database table [Product]");

        if (clientDatabase.recordsCount() == clientRepository.count()) {
            System.out.println("Copy [Client] table finished succefully");
        } else {
            System.out.println("Copy [Client] table finished with error");
        }

        if (productDatabase.recordsCount() == productRepository.count()) {
            System.out.println("Copy [Product] table finished succefully");
        } else {
            System.out.println("Copy [Product] table finished with error");
        }
    }
}
