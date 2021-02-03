package internet_store.core.service.product;

import internet_store.core.domain.Product;
import internet_store.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RandomProductListService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> createRandomProductsList() {
        List<Long> rndId = new ArrayList<>();

        int count = 0;
        boolean flag = true;
        while (flag) {
            long generatedValue = 1 + (long) ((Math.random()) * productRepository.count());
            if (isIdValueNotInList(rndId, generatedValue)) {
                rndId.add(generatedValue);
                count++;
            }
            if (count == 6) {
                flag = false;
            }
        }
        return getRandomProductsList(rndId);
    }

    private List<Product> getRandomProductsList(List<Long> rndId) {
        return rndId.stream().map(i -> productRepository.findById(i))
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
    }

    private boolean isIdValueNotInList(List<Long> rndId, long newValue) {
        return rndId.stream().noneMatch(i -> i == newValue);
    }
}