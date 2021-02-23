package lv.javaguru.app.core.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.domain.Flight;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketUpdateResponse extends Response {

	private String message;

	public TicketUpdateResponse (List<CodeError> errors) {
		super(errors);
	}
}
