package app.exceptions;

/**
 * Неизвестная ошибка.
 */
public class UnknownException extends RuntimeException {
  public UnknownException(String message) {
    super(message);
  }

  @Override
  public String getMessage() {
    return "Неизвестная ошибка: " + super.getMessage();
  }
}
