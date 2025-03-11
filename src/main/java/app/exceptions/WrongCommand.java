package app.exceptions;

/**
 * Ошибка, связанная с неправильным синтаксисом команды.
 */
public class WrongCommand extends RuntimeException {
    public WrongCommand(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Неверный формат команды. Для ознакомления с ситнаксисом команд используйте \"help\".";
    }
}
