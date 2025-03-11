package app;

import app.exceptions.UnknownException;
import app.product.builders.ProductBuilder;

import java.io.*;


public class BackUpManager {
    File file = null;
    ProductBuilder productBuilder;

    /**
     * @param pathToFile путь файла временного сохранения
     */
    public BackUpManager(String pathToFile) {
        file = new File(pathToFile);
    }


    /**
     * Сохраняет массив объектов во временный файл.
     *
     * @param object массив объектов для сохранения
     * @param <E> тип объектов массива
     */
    public <E> void backUp(E ... object) {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));) {
            stream.writeObject(object);
        } catch (Exception e) {
            throw new UnknownException(e.toString());
        }
    }

    /**
     * Читает из файла сохранения билдер продукта.
     * Удаляет временный файл после прочтения.
     *
     * @return Билдер продукта
     */
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

    /**
     * Удаление временного файла.
     */
    public void deleteTempFile() {
        file.delete();
    }
}
