package app.commands;

import app.Application;
import app.exceptions.WrongCommand;

import java.io.BufferedReader;

/**
 * Команда удаления всех элементов из коллекции.
 */
public class Clear implements Command {
    private String name = "clear";
    private String description = "Очистить коллекцию.";

    @Override
    public void execute(Application app, String[] pars, BufferedReader input) {
        if (pars.length != 1) throw new WrongCommand(name);

        app.getProductManager().clear();
    }

    @Override
    public String getName() {return name;}

    @Override
    public String getDescription() {return description;}
}
