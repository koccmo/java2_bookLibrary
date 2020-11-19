package lesson_1.user_handler;

import lombok.Getter;

public class UserInputValue<T> {
    @Getter
    private final T value;

    public UserInputValue(T value) {
        this.value = value;
    }
}