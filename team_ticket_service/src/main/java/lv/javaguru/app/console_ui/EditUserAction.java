package lv.javaguru.app.console_ui;

import lv.javaguru.app.console_ui.Action;
import lv.javaguru.app.console_ui.UIActions;
import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.admin.EditUserRequest;
import lv.javaguru.app.core.response.admin.EditUserResponse;
import lv.javaguru.app.core.services.EditUserService;

import java.util.Scanner;

public class EditUserAction extends Action implements UIActions {

	private final EditUserService editUserService;

	public EditUserAction (EditUserService editUserService) {
		this.editUserService = editUserService;
	}

	private static void adminMode_printEditUser (User currentUser) {
		BaseFunc.printHeader("EDIT USER:", currentUser);
		System.out.println(
				"[1] Edit first name\n" +
						"[2] Edit second name\n" +

						"[0] Back");
	}

	@Override
	public void execute () {
		BaseFunc.printHeader("Enter user ID:");
		long id = BaseFunc.getMenuNumberFromUser();

		EditUserRequest request = new EditUserRequest(id);
		EditUserResponse response = editUserService.execute(request);

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

		EditUserRequest request = new EditUserRequest(user.getId());
		request.setSurname(input);
		EditUserResponse response = editUserService.executeSurnameEdit(request);

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

		EditUserRequest request = new EditUserRequest(user.getId(), input);
		EditUserResponse response = editUserService.updateUserName(request);

		if (response.hasErrors()) {
			response.getErrorList().forEach(r -> System.out.println(r.getField() +
					r.getMessage()));
		}
		else
			System.out.println(response.getMessage());
	}
}


