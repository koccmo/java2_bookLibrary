package internet_store.log;

import lombok.Data;

import java.util.Date;

@Data
public class Events {
    private Long id;
    private Date date;
    private String eventDescription;
}
