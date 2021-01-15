package lv.estore.app.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class CommonUtils {

    public BigDecimal createBigDecimal(final String newPrice) {
        return new BigDecimal(newPrice.replaceAll(",", ".")).abs()
                .setScale(2, RoundingMode.FLOOR);
    }
}
