package app.commands;

import app.Application;
import app.exceptions.WrongCommand;

import java.io.BufferedReader;

/**
 * Команда, выводящая все доступные команды и их описания.
 */
public class Help implements Command {
    private String name = "help";
    private String description = "Выводит список доступных команд с пояснениями.";

    @Override
    public void execute(Application app, String[] pars, BufferedReader input) {
        if (pars.length != 1) throw new WrongCommand(name);
        app.getCommandManager().showAllCommands();
    }

    @Override
    public String getName() {return name;}

    @Override
    public String getDescription() {return description;}
}
