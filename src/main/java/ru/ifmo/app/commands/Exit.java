package app.commands;

import app.Application;
import app.exceptions.WrongCommand;

import java.io.BufferedReader;

/**
 * Реализует команду выхода из приложения.
 */
public class Exit implements Command {
    private String name = "exit";
    private String description = "завершение программы";

    @Override
    public void execute(Application app, String[] pars, BufferedReader input) {
        if (pars.length !=1) throw new WrongCommand(name);
        while (true) {
            try {
                System.out.printf("Вы действительно хотите выйти (все несохранённые данные будут потеряны)? (y/n): ");
                String answer = input.readLine();
                answer = answer == null ? null : answer.trim();
                if (answer == null) {
                    System.out.println("");
                    break;
                } else if (answer.equals("y")) {
                    app.getBackupManager().deleteTempFile();
                    app.stop();
                    break;
                } else if (answer.equals("n")) {
                    System.out.println("");
                    break;
                }
                System.out.println("(⊙ _ ⊙) Неверный формат ввода. Пожалуйста, повторите попытку");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }

    @Override
    public String getName() {return name;}

    @Override
    public String getDescription() {return description;}
}
