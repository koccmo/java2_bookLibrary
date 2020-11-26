package internet_store.date_formats;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateCreator {
    public String createShortDateFormat() {
        LocalDate localDate = LocalDate.now();
        return DateTimeFormatter.ofPattern("dd.MM.yyyy").format(localDate);
    }
}