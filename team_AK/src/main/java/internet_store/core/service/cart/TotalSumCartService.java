package internet_store.core.service.cart;

import internet_store.persistence.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class TotalSumCartService {
    @Autowired
    CartRepository CartRepository;

    public BigDecimal calculateTotalSum() {
        BigDecimal sum = CartRepository.getCartTotalSum();
        if (sum == null) {
            sum = new BigDecimal("0.00");
        }
        return sum;
    }
}