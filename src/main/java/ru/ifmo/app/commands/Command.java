package app.commands;

import app.Application;

import java.io.BufferedReader;

/**
 * Интерфейс для реализации команд.
 */
public interface Command {
    /**
     * Выполение команды.
     *
     * @param app приложение, в котором выполняется команда
     * @param pars параметры команды
     * @param input входной поток, в который будет вводится значение при необходимости
     */
    void execute(Application app, String[] pars, BufferedReader input);
    String getName();
    String getDescription();
}
