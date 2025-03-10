package app.commands;

import app.Application;
import app.exceptions.UnknownException;

import java.io.BufferedReader;

/**
 * Команды, сохраняющая данные из коллекции в файл.
 */
public class Save implements Command{
    private String name = "save";
    private String description = "Сохраняет коллекцию в файл.";

    @Override
    public void execute(Application app, String[] pars, BufferedReader input) {
        try {
            app.getProductManager().saveCollection(System.getenv("LAB5_PATH"));
            app.getBackupManager().deleteTempFile();
            System.out.println("***Данные успешно сохранены***");
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnknownException(e.getMessage());
        }
    }

    @Override
    public String getName() {return name;}

    @Override
    public String getDescription() {return description;}
}
