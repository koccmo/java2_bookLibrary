public class InputValidation {
    public int validateUserMenuChoice(String userStringInput) {
        int choice;
        try {
            choice = Integer.valueOf(userStringInput);
        } catch (Exception e) {
            return -1;
        }
        for (int i = 0; i < 6; i++) {
            if (choice == i) {
                return choice;
            }
        }
        return -1;
    }
}
