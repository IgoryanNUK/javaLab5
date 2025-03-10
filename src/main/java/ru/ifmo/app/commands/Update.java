package app.commands;

import app.Application;
import app.exceptions.UnexceptibleValue;
import app.exceptions.UnknownException;
import app.exceptions.WrongCommand;
import app.product.*;

import java.io.BufferedReader;

import static app.product.EnterManager.*;

/**
 * Команда, обновляющая все значения элемента по заданному id.
 */
public class Update implements Command {
    private String name = "update {id}";
    private String description = "Обновляет элемент по заданному id.";

    @Override
    public void execute(Application app, String[] pars, BufferedReader input) {
        if (pars.length != 2) throw new WrongCommand(name);
        int id;
        try {
            id = Integer.parseInt(pars[1]);
        } catch (NumberFormatException e) {
            throw new WrongCommand(name);
        }
        try {
            Product product = app.getProductManager().getProductById(id);
            if (product == null) {
                System.out.println("Не нашел элементов с указанным id(((");
            } else {
                product.setName(nameEnter(input, "название продукта", app.isScriptRunning()));
                product.setCoordinates(coordinatesEnter(input, app.isScriptRunning()));
                product.setPrice(floatEnter(input, "цена товара", app.isScriptRunning()));
                product.setPartNumber(partNumberEnter(input, app.isScriptRunning()));
                product.setManufactureCost(manufactureCostEnter(input, app.isScriptRunning()));
                product.setUnitOfMeasure(enumEnter(input, UnitOfMeasure.values(), "единица измерения", false, app.isScriptRunning()));
                product.setOwner(personEnter(input, app.isScriptRunning()));
                System.out.println("***Продукт " + product.getName() + " успешно обновлён***");
            }
        } catch (UnexceptibleValue u){
            throw u;
        }catch (Exception e) {
            throw new UnknownException(e.getMessage());
        }
    }

    @Override
    public String getName() {return name;}

    @Override
    public String getDescription() {return description;}
}
