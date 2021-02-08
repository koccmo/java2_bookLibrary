package lv.javaguru.app.console_ui.modes;

import lv.javaguru.app.console_ui.ExitAction;
import lv.javaguru.app.console_ui.LogInAction;
import lv.javaguru.app.console_ui.UserAddAction;
import lv.javaguru.app.core.common.BaseFunc;
import org.springframework.context.ApplicationContext;

public class InitMode {

	private final ApplicationContext context;

	public InitMode (ApplicationContext context) {
		this.context = context;
	}


	public void execute () {
		while (true) {
			printInitMenu();

			int menuNumber = BaseFunc.getMenuNumberFromUser();

			switch (menuNumber) {
				case 1 -> {
					LogInAction logInAction = context.getBean(LogInAction.class);
					logInAction.execute();
				}
				case 2 -> {
					UserAddAction userAddAction = context.getBean(UserAddAction.class);
					userAddAction.execute();
				}
				case 0 -> {
					ExitAction exitAction = context.getBean(ExitAction.class);
					exitAction.execute();
					return;
				}
				default -> {
					System.out.println("\nWrong input!\n");
				}
			}
		}
	}

	private static void printInitMenu () {
		BaseFunc.printHeader("LOGIN");
		System.out.println(
				"[1] Login\n" +
						"[2] Register\n\n" +
						"[0] Exit");
	}
}
