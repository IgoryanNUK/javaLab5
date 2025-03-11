package app.exceptions;

/**
 * Ошибка, связанная с вводом несуществующей команды.
 */
public class NoSuchCommand extends RuntimeException {
    public NoSuchCommand(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Такой команды не существует. Для ознакомления с доступными командами используйте \"help\".";
    }
}
