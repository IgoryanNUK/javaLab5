package app.commands;

import app.Application;
import app.exceptions.WrongCommand;

import java.io.*;

/**
 * Реализует команду выполнения скрипта.
 */
public class ExecuteScript implements Command {
    private String name = "execute_script {path}";
    private String descriprion = "Выполняет команды из скрипта. В качестве обязательного параметра укажите полный путь к файлу.";

    @Override
    public void execute(Application app, String[] pars, BufferedReader input) {
        if (pars.length != 2) throw new WrongCommand(name);
        File file = new File(pars[1]);
        try {
            app.setScriptReading(true);
            BufferedReader fileInput = new BufferedReader(new FileReader(file));
            while (fileInput.ready()) {
                app.getCommandManager().handleCommand(fileInput);
            }
            fileInput.close();
        } catch (FileNotFoundException e) {
            System.out.println("Не нашёл указанного файла(");;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            app.setScriptReading(false);
        }
    }

    @Override
    public String getName() {return name;}

    @Override
    public String getDescription() {return descriprion;}
}
