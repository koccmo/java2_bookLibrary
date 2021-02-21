package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.UserAddRequest;
import lv.javaguru.app.core.response.UserAddResponse;
import lv.javaguru.app.core.services.UserAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAddAction extends Action implements UIActions {

	@Autowired
	private UserAddService userAddService;


	@Override
	public void execute () {
		User user = fillRegistrationForm();

		UserAddRequest request = new UserAddRequest(user.getUsername(), user.getPassword());
		UserAddResponse response = userAddService.execute(request);

		if (response.hasErrors())
			response.getErrorList().forEach(System.out::println);
		else
			System.out.println(response.getMessage());
	}

}
