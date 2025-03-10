package app.product.builders;

import app.product.Color;
import app.product.Country;
import app.product.Person;

public class PersonBuilder {
    private String name = null;
    private float height = 0;
    private Color eyeColor= null;
    private Country nationality = null;

    public void setName(String name) { this.name = name;}

    public void setHeight(float height) { this.height= height;}

    public void setEyeColor(Color ec) {this.eyeColor = ec;}

    public void setNationality(Country nationality) {this.nationality=nationality; }

    public Person createPerson() {
        return new Person(name, height, eyeColor, nationality);
    }
}
