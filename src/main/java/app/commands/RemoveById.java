package app.commands;

import app.Application;
import app.exceptions.WrongCommand;

import java.io.BufferedReader;

/**
 * Команда, удаляющая элемент по заданному значению ID.
 */
public class RemoveById implements Command {
    private String name = "remove_by_id {id}";
    private String description = "Удаляет продукт из коллеции по указанному id. Обязательный целочисленный аргумент - id продукта";

    @Override
    public void execute(Application app, String[] pars, BufferedReader input) {
        if (pars.length != 2) throw new WrongCommand(name);
        try {
            int id = Integer.valueOf(pars[1]);
            if(app.getProductManager().removeIf(e -> e.getId() == id)){
                System.out.println("Элемент(ы) успешно удален(ы).");
            } else {
                System.out.println("Не нашел элемента с указанным id(((");
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
