package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.UserEditRequest;
import lv.javaguru.app.core.response.admin.EditUserResponse;
import lv.javaguru.app.core.services.UserEditService;

import java.util.Scanner;

public class UserUpdateAction extends Action implements UIActions {

	private final UserEditService userEditService;

	public UserUpdateAction (UserEditService userEditService) {
		this.userEditService = userEditService;
	}

	private static void adminMode_printEditUser (User currentUser) {
		BaseFunc.printHeader("EDIT USER:", currentUser);
		System.out.println(
				"[1] Edit first name\n" +
						"[2] Edit second name\n" +

						"[0] Cancel");
	}

	@Override
	public void execute () {
		BaseFunc.printHeader("Enter user ID:");
		long id = BaseFunc.getMenuNumberFromUser();

		UserEditRequest request = new UserEditRequest(id);
		EditUserResponse response = userEditService.execute(request);

		if (response.hasErrors()) {
			response.getErrorList().forEach(r -> System.out.println(r.getField() +
					r.getMessage()));
		}
		else
			adminMode_UserEditMenu(response.getUser());
	}

	private void adminMode_UserEditMenu (User user) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			adminMode_printEditUser(user);

			int menuNumber = BaseFunc.getMenuNumberFromUser();

			switch (menuNumber) {
				case 1 -> editNameAction(user, scanner);
				case 2 -> editSurnameAction(user, scanner);

				case 0 -> {
					return;
				}
			}
		}
	}


	private void editSurnameAction (User user, Scanner scanner) {
		BaseFunc.printHeader("Enter new surname:");
		String input = scanner.nextLine();

		UserEditRequest request = new UserEditRequest(user.getId());
		UserEditRequest.Surname surname = request.new Surname(input);

		EditUserResponse response = userEditService.execute(surname);

		if (response.hasErrors()) {
			response.getErrorList().forEach(r -> System.out.println(r.getField() +
					r.getMessage()));
		}
		else
			System.out.println(response.getMessage());
	}

	private void editNameAction (User user, Scanner scanner) {
		BaseFunc.printHeader("Enter new name:");
		String input = scanner.nextLine();

		UserEditRequest request = new UserEditRequest(user.getId());
		UserEditRequest.Name name = request.new Name(input);

		EditUserResponse response = userEditService.execute(name);

		if (response.hasErrors()) {
			response.getErrorList().forEach(r -> System.out.println(r.getField() +
					r.getMessage()));
		}
		else
			System.out.println(response.getMessage());
	}
}


