package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.RegistrationRequest;
import lv.javaguru.app.core.response.RegistrationResponse;
import lv.javaguru.app.core.services.RegisterService;

import java.util.Scanner;

public class RegisterAction extends Action implements UIActions {
	private final RegisterService registerService;

	public RegisterAction (RegisterService registerService) {
		this.registerService = registerService;
	}

	@Override
	public void execute () {
		User newUser = fillRegistrationForm();

		RegistrationRequest request = new RegistrationRequest(newUser);
		RegistrationResponse response = registerService.execute(request);

		if (response.hasErrors())
			response.getErrorList().forEach(System.out::println);
		else
			System.out.println(response.getMessage());
	}

}
