package app.exceptions;

/**
 * Ошибка, связанная со вводом значения, не удовлетворяющего ограничениям.
 */
public class UnexceptibleValue extends RuntimeException {
    public UnexceptibleValue(String fieldName) {
        super("Неприемлемое значение для поля \"" + fieldName + "\"");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
