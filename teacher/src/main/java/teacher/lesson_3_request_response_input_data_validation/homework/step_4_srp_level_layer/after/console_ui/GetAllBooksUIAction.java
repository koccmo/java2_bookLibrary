package teacher.lesson_3_request_response_input_data_validation.homework.step_4_srp_level_layer.after.console_ui;

import teacher.lesson_3_request_response_input_data_validation.homework.step_4_srp_level_layer.after.services.GetAllBooksService;

public class GetAllBooksUIAction implements UIAction {

	private GetAllBooksService getAllBooksService;

	public GetAllBooksUIAction(GetAllBooksService getAllBooksService) {
		this.getAllBooksService = getAllBooksService;
	}

	@Override
	public void execute() {
		System.out.println("Book list: ");
		getAllBooksService.execute().forEach(System.out::println);
		System.out.println("Book list end.");
	}
}