package lv.estore.app.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class CommonUtils {

    public static Long getLong(final String value) {
        return Long.parseLong(value);
    }

    public static BigDecimal getBigDecimal(final String value) {
        return new BigDecimal(value.replaceAll(",", ".")).abs()
                .setScale(2, RoundingMode.FLOOR);
    }

    public static Date transformDate(LocalDateTime date){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String strDate = date.format(dtf);
        Date created;
        try {
            created = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strDate);
        } catch (ParseException e) {
            created = null;
        }
        return created;
    }
}
