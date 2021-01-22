package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.UserAddRequest;
import lv.javaguru.app.core.response.RegistrationResponse;
import lv.javaguru.app.core.services.UserAddService;

public class UserAddAction extends Action implements UIActions {
	private final UserAddService userAddService;

	public UserAddAction (UserAddService userAddService) {
		this.userAddService = userAddService;
	}

	@Override
	public void execute () {
		User newUser = fillRegistrationForm();

		UserAddRequest request = new UserAddRequest(newUser);
		RegistrationResponse response = userAddService.execute(request);

		if (response.hasErrors())
			response.getErrorList().forEach(System.out::println);
		else
			System.out.println(response.getMessage());
	}

}
