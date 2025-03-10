package app.commands;

import app.Application;
import app.exceptions.UnexceptibleValue;
import app.exceptions.WrongCommand;
import app.product.UnitOfMeasure;

import java.io.BufferedReader;

/**
 * Реализует команду вывода всех элементов, единица измерения которых больше заданных.
 */
public class FilterGreaterThanUnitOfMeasure implements Command {
    private String name = "filter_gtuom {unitOfMeasure}";
    private String description = "Сохраняет коллекцию в файл.";

    @Override
    public void execute(Application app, String[] pars, BufferedReader input) {
        if (pars.length != 2) throw new WrongCommand(name);

        UnitOfMeasure request = null;
        for (UnitOfMeasure u : UnitOfMeasure.values()) {
            if (u.toString().equals(pars[1])) {
                request = u;
            }
        }
        if (request == null) {
            throw new UnexceptibleValue("UnitOfMeasure");
        }
        else {
            final UnitOfMeasure value = request;
            app.getProductManager().printIf(e -> e.getUnitOfMeasure().compareTo(value) < 0);
        }
    }

    @Override
    public String getName() {return name;}

    @Override
    public String getDescription() {return description;}
}
