package lv.javaguru.app;

import lv.javaguru.app.console_ui.modes.AdminMode;
import lv.javaguru.app.console_ui.modes.InitMode;
import lv.javaguru.app.console_ui.modes.UserMode;
import lv.javaguru.app.dependency_injection.ApplicationContext;
import lv.javaguru.app.dependency_injection.DIApplicationContextBuilder;


public class Main {
	private static final ApplicationContext context = new DIApplicationContextBuilder().build("lv.javaguru.app");

	public static void main (String[] args) {
		AdminMode.setInstance(context);
		UserMode.setInstance(context);

		InitMode initMode = new InitMode(context);
		initMode.fillDb();
		initMode.execute();
	}


}

