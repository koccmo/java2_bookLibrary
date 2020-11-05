package teacher.applications.book_library.ui;

public class ExitProgramUIAction implements UIAction {
	@Override
	public void execute() {
		System.out.println("Good by!");
		System.exit(0);
	}
}
