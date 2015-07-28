package com.sysgears.application.commands;

import com.sysgears.application.converter.Converter;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains a list of the types of permissible commands.
 * All system commands should be marked by <code>Command.SYSTEM_COMMAND</code> in a description.
 */
public enum Command {
    HISTORY("history", "Display the history of all calculations."),
    HISTORY_UNIQUE("history unique", "Display all unique calculations."),
    HELP("help", "Display a list of all available commands."),
    EXIT("exit", "Exit the program."),
    EVALUATE(".?\\d+.?", Command.SYSTEM_COMMAND),
    UNKNOWN_COMMAND("", Command.SYSTEM_COMMAND);

    /**
     * Marker which indicates a system command.
     */
    public static final String SYSTEM_COMMAND = "{system}";

    /**
     * The command name.
     */
    private final String commandName;

    /**
     * The command description.
     */
    private final String description;

    /**
     * Regex filter to search for commands in a string.
     */
    private final String regex;

    /**
     * @param commandName a command name
     * @param description description of a command
     */
    private Command(final String commandName, final String description) {
        this.commandName = commandName;
        this.description = description;
        this.regex = (description.equals(SYSTEM_COMMAND)) ? commandName : Converter.buildRegex(commandName);
    }

    /**
     * Parses an input expression and returns a command type from the <code>Command</code> class.
     *
     * @param expression an input expression
     * @return command
     */
    public static Command parse(final String expression) {
        Command result = Command.UNKNOWN_COMMAND;
        for (Command command : Command.values()) {
            if (Converter.findString(expression, command.getRegex(), true)) {
                result = command;
                break;
            }
        }

        return result;
    }

    /**
     * Returns a list of all commands with a description.
     *
     * @return list of commands
     */
    public static List<String> getHelpMessage() {
        List<String> result = new ArrayList<String>();
        for (Command command : Command.values()) {
            if (!command.description.equals(SYSTEM_COMMAND)) {
                result.add(command.commandName + " - " + command.description);
            }
        }

        return result;
    }

    /**
     * Returns a regex filter for a command.
     *
     * @return regular expression
     */
    public String getRegex() {
        return this.regex;
    }
}