package app.commands;

import app.Application;
import app.exceptions.NoSuchCommand;
import app.exceptions.RecursiCallError;
import app.exceptions.UnknownException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashMap;

/**
 * Класс управления командами приложения.
 * */
public class CommandManager {
    private HashMap<String, Command> commands = new HashMap<>();
    private Application app;
    private ArrayDeque<String> history = new ArrayDeque<>(14);

    /**
     * @param app приложение, которму принадлежит менеджер
     */
    public CommandManager(Application app) {
        this.app = app;

        addCommand(new Help());
        addCommand(new Info());
        addCommand(new Show());
        addCommand(new Exit());
        addCommand(new Add(), new Update(), new RemoveById(), new Clear(), new Save(),
                new ExecuteScript(), new Exit(), new RemoveGreater(), new RemoveLower(),
                new History(), new RemoveByPartNumber(), new FilterStartsWithPartNumber(),
                new FilterGreaterThanUnitOfMeasure());

        addCommand(new History());
        addCommand(new ExecuteScript(), new Add(), new RemoveById(), new Update(), new Save(), new RemoveGreater(),
                new RemoveLower());
    }

    /** Обрабатывает команду из входного потока.
     *
     * @param input входной поток
     */
    public void handleCommand(BufferedReader input) {
        try {
            String commandRequest[] = input.readLine().trim().split(" ");
            if (!commandRequest[0].equals("")) {
                Command command = getCommandByRequest(commandRequest[0]);
                if (command == null) {
                    throw new NoSuchCommand("command");
                } else {
                    command.execute(app, commandRequest, input);
                    addInHistory(commandRequest[0]);
                }
            }
        } catch (StackOverflowError e) {
            throw new RecursiCallError(e.getMessage());
        } catch (NoSuchCommand n) {
            throw n;
        } catch (Exception e) {
            throw new UnknownException(e.getMessage());
        }
    }

    /**
     * Добавляет команды в функционал приложения.
     * @param cs команды
     */
    public void addCommand(Command ...cs) {
        for (Command c : cs) {
            commands.put(c.getName().split(" ")[0], c);
        }
    }

    /**
     * Возвращает объект команды по её имени.
     * @param request имя команды
     * @return объект команды
     */
    public Command getCommandByRequest(String request) {
        return commands.get(request);
    }

    private void addInHistory(String command) {
        if (history.size() == 14) {
            history.removeFirst();
        }
        history.add(command);
    }

    /**
     * Выводит историю команд. Выводит последние 14 корректно введённых команд.
     */
    public void showHistory() {
        history.forEach(System.out::println);
    }

    /**
     * Выводит список всех команд с их описанием.
     */
    public void showAllCommands() {
        for (Command c : commands.values()) {
            System.out.println(c.getName() + " --- " + c.getDescription());
        }
    }
}
