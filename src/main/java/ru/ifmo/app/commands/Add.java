package app.commands;

import app.Application;
import app.BackUpManager;
import app.exceptions.UnexceptibleValue;
import app.exceptions.UnknownException;
import app.exceptions.WrongCommand;
import app.product.*;
import app.product.builders.ProductBuilder;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import static app.product.EnterManager.*;

/**
 * Команда добавления элемента в коллекцию.
 */
public class Add implements Command {
    private String name = "add";
    private String description = "Добавляет новый продукт в коллекцию";

    @Override
    public void execute(Application app, String[] pars, BufferedReader input) {
        if (pars.length != 1) throw new WrongCommand(name);
        try {
            System.out.println("*******Создание продукта*******");

            ProductBuilder pb = new ProductBuilder(app.getBackupManager());

            pb.setName(nameEnter(input, "название продукта", app.isScriptRunning()));
            pb.setCoordinates(coordinatesEnter(input, app.isScriptRunning()));
            pb.setPrice(floatEnter(input, "стоимость продукта", app.isScriptRunning()));
            pb.setPartNumber(partNumberEnter(input, app.isScriptRunning()));
            pb.setManufactureCost(manufactureCostEnter(input, app.isScriptRunning()));
            pb.setUnitOfMeasure(enumEnter(input, UnitOfMeasure.values(), "единица измерения", false, app.isScriptRunning()));
            pb.setOwner(personEnter(input, app.isScriptRunning()));

            app.getProductManager().addProduct(pb.createProduct());
            if (app.isScriptRunning()) {
                System.out.println();
            }
            System.out.println("*******Продукт " + name + " успешно добавлен в коллекцию*******");
        } catch (UnexceptibleValue u) {
            throw u;
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnknownException(e.getMessage());
        }
    }


    public void execute(Application app, BufferedReader input, ProductBuilder pb) {
        try {
            System.out.println("*******Создание продукта*******");
            boolean isScriptRunning = app.isScriptRunning();

            String name;
            if (pb.getName() == null){
                name = nameEnter(input, "название продукта", isScriptRunning);
                pb.setName(name);
            } else {
                name = pb.getName();
                System.out.println("Введитё имя: " + name);
            }


            if (pb.getCoordinates() == null){
                pb.setCoordinates(coordinatesEnter(input, isScriptRunning));
            } else {
                Coordinates c = pb.getCoordinates();
                System.out.println("Введите координату x: " + c.getX());
                System.out.println("Введите координату y: " + c.getY());
            }

            if (pb.getPrice() == 0) {
                pb.setPrice(floatEnter(input, "стоимость продукта", isScriptRunning));
            } else {
                System.out.println("Введите стоимость продукта: " + pb.getPrice());
            }

            if (pb.getPartNumber() == null) {
                pb.setPartNumber(partNumberEnter(input, isScriptRunning));
            } else {
                System.out.println("Введите партийный номер: " + pb.getPartNumber());
            }

            if (pb.getManufactureCost() == null) {
                pb.setManufactureCost(manufactureCostEnter(input, isScriptRunning));
            } else {
                System.out.println("Введите стоимость производства: " + pb.getManufactureCost());
            }

            if (pb.getUnitOfMeasure() == null) {
                pb.setUnitOfMeasure(enumEnter(input, UnitOfMeasure.values(), "единица измерения", false, isScriptRunning));
            } else {
                System.out.println("Введите единицу измерения: " + pb.getUnitOfMeasure());
            }

            if (pb.getOwner() == null) {
                pb.setOwner(personEnter(input, isScriptRunning));
            } else {
                Person p = pb.getOwner();
                System.out.println("Введите имя владельца: " + p.getName());
                System.out.println("Введите рост владельца: " + p.getHeight());
                System.out.println("Введите цвет глаз владельца: " + p.getEyeColor());
                System.out.println("Введите национальность владельца: " + p.getNationality());
            }

            app.getProductManager().addProduct(pb.createProduct());
            if (app.isScriptRunning()) {
                System.out.println();
            }
            System.out.println("*******Продукт " + name + " успешно добавлен в коллекцию*******");
        } catch (UnexceptibleValue u) {
            throw u;
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
