package teacher.lesson_3_request_response_input_data_validation.homework.step_4_srp_level_layer.after.console_ui;

public class ExitUIAction implements UIAction {
	@Override
	public void execute() {
		System.out.println("Good by!");
		System.exit(0);
	}
}
