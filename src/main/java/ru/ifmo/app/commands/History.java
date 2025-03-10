package app.commands;

import app.Application;

import java.io.BufferedReader;

/**
 * Команда, выводящая последние 14 команд.
 */
public class History implements Command {
    private String name = "history";
    private String description = "Выводит последние 14 команд.";

    @Override
    public void execute(Application app, String[] pars, BufferedReader input) {
        app.getCommandManager().showHistory();
    }

    @Override
    public String getName() {return name;}

    @Override
    public String getDescription() {return description;}
}
