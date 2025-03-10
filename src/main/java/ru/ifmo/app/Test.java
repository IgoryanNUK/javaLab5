package app;

/**
 * Класс запуска программы.
 *
 * @author Igor Kuznetsov
 * @version 1.1
 */
public class Test{
    public static void main(String ... args) {
        Application app = new Application();
        try {
            app.run();
        } catch (Exception e) {
            System.out.println("Разраб дурак: " + e.toString());
        }

    }
}