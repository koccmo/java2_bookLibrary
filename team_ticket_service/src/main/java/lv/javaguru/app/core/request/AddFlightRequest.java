package lv.javaguru.app.core.request;

import lombok.*;
import lv.javaguru.app.core.domain.Ticket;
import lv.javaguru.app.core.domain.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddFlightRequest {

	private User user;

	private Ticket ticket;

}
