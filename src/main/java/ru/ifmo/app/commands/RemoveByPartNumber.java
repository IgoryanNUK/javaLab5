package app.commands;

import app.Application;
import app.exceptions.WrongCommand;

import java.io.BufferedReader;

/**
 * Команда, удаляющая элемент по заданному партийному номеру.
 */
public class RemoveByPartNumber implements Command{
    private String name = "remove_any_by_part_number {partNumber}";
    private String description = "Удалить элемент по заданному партийному номеру";

    @Override
    public void execute(Application app, String[] pars, BufferedReader input) {
        if (pars.length != 2) throw new WrongCommand(name);

        if (app.getProductManager().removeIf(e -> {
            String pn = e.getPartNumber();
            if(pn == null) {return false;}
            else {return pn.equals(pars[1]);}})) {
            System.out.println("Элемент успешно удалён.");
        } else {
            System.out.println("Не нашёл элемента с указанным партийным номером(");
        }
    }

    @Override
    public String getName() {return name;}

    @Override
    public String getDescription() {return description;}
}
