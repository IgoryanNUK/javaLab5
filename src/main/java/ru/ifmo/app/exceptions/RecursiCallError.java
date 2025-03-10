package app.exceptions;

public class RecursiCallError extends RuntimeException {
    public RecursiCallError(String message) {
      super(message);
    }

    @Override
    public String getMessage() {
      return "Произошёл рекурсивный вызов. Не делайте так, пожалуйста.";
    }
}
