package lv.javaguru.app;

import lv.javaguru.app.console_ui.modes.InitMode;


public class Main {

	private static final InitMode initMode = new InitMode();

	public static void main (String[] args) {
		initMode.fillDb();
		initMode.execute();
	}


}

