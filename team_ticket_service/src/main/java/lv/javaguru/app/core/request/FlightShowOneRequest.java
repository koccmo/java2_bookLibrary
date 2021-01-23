package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.User;

public class FlightShowOneRequest {
	private final Long id;

	public FlightShowOneRequest (Long id) {
		this.id = id;
	}

	public Long getId () {
		return id;
	}
}
