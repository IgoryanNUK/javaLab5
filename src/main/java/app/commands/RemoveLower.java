package app.commands;

import app.Application;
import app.exceptions.WrongCommand;

import java.io.BufferedReader;

/**
 * Команда, удаляющая все элементы, id которых меньше, чем заданный.
 */
public class RemoveLower implements Command {
    private String name = "remove_lower {id}";
    private String description = "Удаляет все элементы коллекции, id которых меньше, чем указанный.";

    @Override
    public void execute(Application app, String[] pars, BufferedReader input) {
        if (pars.length != 2) throw new WrongCommand(name);

        try {
            int id = Integer.parseInt(pars[1]);
            if (app.getProductManager().removeIf(e -> e.getId() < id)) {
                System.out.println("Элемеент(ы) успешно удален(ы)");
            } else {
                System.out.println("Не нашёл элементов с подходящими id(((");
            }
        } catch (NumberFormatException e) {
            throw new WrongCommand(name);
        }
    }

    @Override
    public String getName() {return name;}

    @Override
    public String getDescription() {return description;}
}
