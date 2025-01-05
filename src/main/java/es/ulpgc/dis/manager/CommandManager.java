package es.ulpgc.dis.manager;

import es.ulpgc.dis.control.CalculateMoneyCommand;
import es.ulpgc.dis.control.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private Map<String, Command> commands = new HashMap<>();

    public CommandManager() {
        addCommands();
    }

    private void addCommands() {
        commands.put("Calculate", new CalculateMoneyCommand());
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }
}
