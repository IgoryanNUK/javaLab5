package app;

import app.exceptions.UnknownException;
import app.product.builders.ProductBuilder;

import java.io.*;


public class BackUpManager {
    File file = null;
    ProductBuilder productBuilder;

    public BackUpManager(String pathToFile) {
        file = new File(pathToFile);
    }

    public <E> void backUp(E ... object) {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));) {
            for (E o : object) {
                stream.writeObject(o);
            }
        } catch (Exception e) {
            throw new UnknownException(e.toString());
        }
    }

    public ProductBuilder readBackUp() {
        if (file.exists()) {
            try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file))) {
                productBuilder = ((ProductBuilder[]) stream.readObject())[0];
                stream.close();
                file.delete();
                return productBuilder;
            } catch (Exception e) {
                System.out.println("!!! Не удалось считать автоматичекое сохранение !!!");
            }
        }
        return null;
    }

    public void deleteTempFile() {
        file.delete();
    }
}
