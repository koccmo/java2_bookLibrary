package lv.javaguru.app.core.request.ticket_update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTicketSeat {

	private Long id;

	private String ticketNewSeat;

}
