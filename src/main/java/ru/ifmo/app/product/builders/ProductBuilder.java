package app.product.builders;

import app.BackUpManager;
import app.product.Coordinates;
import app.product.Person;
import app.product.Product;
import app.product.UnitOfMeasure;

import java.io.Externalizable;
import java.io.Serializable;

public class ProductBuilder implements Serializable {
    private String name = null;
    private Coordinates coordinates = null;
    private float price = 0;
    private String partNumber = null;
    private Double manufactureCost = null;
    private UnitOfMeasure unitOfMeasure = null;
    private Person owner = null;
    transient private BackUpManager backUpManager;

    public ProductBuilder(BackUpManager bm) {
        backUpManager = bm;
    }

    public void setBackUpManager(BackUpManager bm) {
        backUpManager = bm;
    }

    public void print() {
        System.out.println(name + coordinates+price+ partNumber+ manufactureCost);
    }

    public void setName(String name) {
        this.name = name;
        save();
    }

    public String getName() {return name;}

    public void setCoordinates(Coordinates c) {
        coordinates = c;
        save();
    }

    public Coordinates getCoordinates() {return coordinates;}

    public void setPrice(float price) {
        this.price = price;
        save();
    }

    public float getPrice() {
        return price;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
        save();
    }

    public String getPartNumber() { return partNumber;}

    public void setManufactureCost(Double mc) {
        manufactureCost = mc;
        save();
    }

    public Double getManufactureCost() {return manufactureCost;}

    public void setUnitOfMeasure(UnitOfMeasure uom) {
        unitOfMeasure = uom;
        save();
    }

    public UnitOfMeasure getUnitOfMeasure() {return unitOfMeasure;}

    public void setOwner(Person owner) {
        this.owner = owner;
        save();
    }

    public Person getOwner() {return owner;}

    public Product createProduct() {
        return new Product(name, coordinates, price, partNumber, manufactureCost, unitOfMeasure, owner);
    }

    private void save() {
        backUpManager.backUp(this);
    }
}
