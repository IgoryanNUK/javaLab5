package app.commands;

import app.Application;
import app.ProductManager;
import app.exceptions.WrongCommand;

import java.io.BufferedReader;

/**
 * Команда, выводящая информацию о коллекции.
 */
public class Info implements Command {
    private String name = "info";
    private String description = "Выводит информацио о коллекции.";

    @Override
    public void execute(Application app, String[] pars, BufferedReader input) {
        if (pars.length != 1) throw new WrongCommand(name);

        ProductManager pm = app.getProductManager();
        System.out.println("Данные о коллекции:" +
                "\nтип: " + pm.getCollectionName() +
                "\nдата инициализации: " + pm.getInitDate() +
                "\nколичество элементов: " + pm.getSize());
    }

    @Override
    public String getName() {return name;}

    @Override
    public String getDescription() {return description;}
}
