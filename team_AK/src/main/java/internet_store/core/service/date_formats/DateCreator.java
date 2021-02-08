package internet_store.core.service.date_formats;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateCreator {
    public String createShortDateFormat() {
        LocalDate localDate = LocalDate.now();
        return DateTimeFormatter.ofPattern("ddMMyyyy").format(localDate);
    }
}