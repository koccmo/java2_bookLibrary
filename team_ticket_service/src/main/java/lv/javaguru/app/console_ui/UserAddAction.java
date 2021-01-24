package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.UserAddRequest;
import lv.javaguru.app.core.response.UserAddResponse;
import lv.javaguru.app.core.services.UserAddService;
import lv.javaguru.app.dependency_injection.DIComponent;
import lv.javaguru.app.dependency_injection.DIDependency;

@DIComponent
public class UserAddAction extends Action implements UIActions {

	@DIDependency
	private UserAddService userAddService;


	@Override
	public void execute () {
		User newUser = fillRegistrationForm();

		UserAddRequest request = new UserAddRequest(newUser);
		UserAddResponse response = userAddService.execute(request);

		if (response.hasErrors())
			response.getErrorList().forEach(System.out::println);
		else
			System.out.println(response.getMessage());
	}

}
