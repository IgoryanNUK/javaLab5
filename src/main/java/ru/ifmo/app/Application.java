package app;

import app.commands.*;
import app.exceptions.UnknownException;
import app.product.builders.ProductBuilder;


import java.io.*;
import java.lang.reflect.Array;
import java.util.List;
import java.util.stream.Stream;

/**
 * Главный класс приложения.
 */
public class Application {
    private BufferedReader enter = new BufferedReader(new InputStreamReader(System.in));
    /** состояние приложения */
    private boolean isRunning = true;
    /** мэнэджер коллекции */
    private ProductManager productManager = new ProductManager();
    /** обработчик команд */
    private CommandManager commandManager = new CommandManager(this);
    /** мэнэджер автосохранения */
    private BackUpManager tempManager = null;
    /** отображает, читается ли скрипт */
    private boolean scriptReading = false;


    /*public static void main(String... args) {
        run();
    }*/

    public Application() {
        String path = System.getenv("LAB5_PATH");
        tempManager = new BackUpManager(path.substring(0, path.lastIndexOf("\\")) + "\\tempFile");
    }

    /** Запускает приложение. */
    public void run() throws Exception {
        /*try {
            String path = System.getenv("LAB5_PATH");
            checkUnsaved(path.substring(0, path.lastIndexOf("\\")));
        } catch(Exception e) {
            System.out.println(e);
        }*/
        try {
            readCollection(System.getenv("LAB5_PATH"));
        } catch (Exception e) {
            System.out.println("***Не удалось считать данные из файла(" + e.getMessage() + "***");
        }
        checkBackUp();
        while (isRunning) {
            try {
                commandManager.handleCommand(enter);
            } catch (Exception e) {
                System.out.println("(⊙ _ ⊙) " + e.getMessage());
            }
        }
    }

    /** Останавливает приложение. */
    public void stop() {
        isRunning = false;
    }

    /** Возвращает менеджер коллекции продуктов.
     *
     * @return менеджер коллекции продуктов.
     */
    public ProductManager getProductManager() {
        return productManager;
    }

    /** Возвращает менеджер команд приложения. */
    public CommandManager getCommandManager() {return commandManager;}

    /** Возвращает менеджера резервного копирования */
    public BackUpManager getBackupManager() {
        return tempManager;
    }

    /**
     * Проверяет, читается ли сейчас скрипт.
     * Возвращает true, если запущен скрипт
     */
    public boolean isScriptRunning() { return scriptReading; }

    /**
     * Сообщает приложению о чтении и завершении чтения скрипта.
     *
     * @param isReading читается ли сейчас скрипт
     */
    public void setScriptReading(boolean isReading) { this.scriptReading = isReading; }

    /**
     * Заполняет коллекцию сохранёнными данными.
     * Считывает объекты продуктов, сохранённые в переменной файл, на который ссылается переенная окружения окружения "LAB5_PATH".
     *
     * @param filePath путь до файла сохранения
     */
    private void readCollection(String filePath) {
        try {
            productManager.readCollection(filePath);
        } catch (FileNotFoundException f) {
            System.out.println("***Не получилось считать данные (отсутствует файл сохранения)***");
        }
    }

    /**
     * Обрабатывает автоматические сохранения.
     * Если обнаруживается временный файл, в консоль выводится сообщение об аварийном завершении приложениня и восстанавливает ввёдённые пользователем данные.
     */
    private void checkBackUp() {
        try {
            ProductBuilder builder = tempManager.readBackUp();
            if (builder != null) {
                System.out.println("!!! Прошлый сеанс работы с приложением был завершён некорректно. Введённые данные восстановлены. !!!");
                builder.setBackUpManager(tempManager);
                ((Add) commandManager.getCommandByRequest("add")).execute(this, enter, builder);
            }
        } catch (Exception e) {
            throw new UnknownException(e.toString());
        }

    }
}
