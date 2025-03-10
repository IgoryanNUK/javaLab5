package app.commands;

import app.Application;
import app.exceptions.WrongCommand;

import java.io.BufferedReader;

/**
 * Реализует комнаду по выводу всех элементов, партийный номер которых начинается с заданной строки.
 */
public class FilterStartsWithPartNumber implements Command {
    private String name = "filter_starts_with_part_number {string}";
    private String description = "Вывести все элементи, партийный номер которых начинается с указанной строки.";

    @Override
    public void execute(Application app, String[] pars, BufferedReader input) {
        if (pars.length != 2) throw new WrongCommand(name);

        app.getProductManager().printIf(e -> {
            String pn = e.getPartNumber();
            if (pn == null) return false;
            else return pn.startsWith(pars[1]);
        });
    }

    @Override
    public String getName() {return name;}

    @Override
    public String getDescription() {return description;}
}
