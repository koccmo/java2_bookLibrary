package lv.javaguru.app.console_ui.admin_side;

import lv.javaguru.app.console_ui.Action;
import lv.javaguru.app.console_ui.UIActions;
import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.services.admin_side.ManageTicketService;

public class ManageTicketAction extends Action implements UIActions {

	private final ManageTicketService manageTicketService;

	public ManageTicketAction (ManageTicketService manageTicketService) {
		this.manageTicketService = manageTicketService;
	}

	public void execute () {


	}

	private static void printAdminMode_manageUserTickets (User currentUser) {
		BaseFunc.printHeader("", currentUser);
		BaseFunc.printLineSeparator();
		System.out.println(
				"[1] Edit ticket\n" +
						"[2] Delete ticket\n" +

						"[0] Back"
		);
	}
}
