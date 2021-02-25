package lv.javaguru.app.core.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.app.core.domain.Ticket;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShowTicketByIdResponse {

	private Ticket ticket;

}
