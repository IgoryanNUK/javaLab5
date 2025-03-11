package app.commands;

import app.Application;
import app.exceptions.WrongCommand;

import java.io.BufferedReader;

/**
 * Команда, выводящая все элементы из коллекции.
 */
public class Show implements Command {
    private String name = "show";
    private String description = "выводит все элементы коллекции";

    @Override
    public void execute(Application app, String[] pars, BufferedReader input) {
        if (pars.length!=1) {
            throw new WrongCommand(name);
        }
        System.out.println("[");
        app.getProductManager().println();
        System.out.println("]");
    }

    @Override
    public String getName() {return name;}

    @Override
    public String getDescription() {return description;}
}
