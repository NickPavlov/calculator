package com.sysgears.calculator.model.commands;

import com.sysgears.calculator.model.commands.util.Command;
import com.sysgears.calculator.model.commands.util.Type;
import com.sysgears.calculator.model.util.StringMatcher;
import com.sysgears.calculator.model.util.RegexCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * The <code>Commands</code> class contains a set of permissible commands.
 */
public enum Commands {

    /**
     * User commands.
     */
    HISTORY("history", "Display the history of all calculations.", Type.USER),
    HISTORY_UNIQUE("history unique", "Display all unique calculations.", Type.USER),
    HELP("help", "Display a list of all available commands.", Type.USER),
    EXIT("exit", "Exit the program.", Type.USER),

    /**
     * System commands.
     */
    EVALUATE(".?\\d+.?", "", Type.SYSTEM),
    UNKNOWN_COMMAND("", "", Type.SYSTEM);

    /**
     * Operation name.
     */
    private final String name;

    /**
     * Operation type.
     */
    private final Type type;

    /**
     * Operation description.
     */
    private final String description;

    /**
     * Regex filter to search for commands in a string.
     */
    private final String regex;

    /**
     * @param name        the command name
     * @param type        the command type
     * @param description the description of the command
     */
    Commands(final String name, final String description, final Type type) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.regex = (type == Type.USER)
                ? RegexCreator.createAtBeginning(RegexCreator.createWithSpaces(name))
                : name;
    }

    /**
     * Returns the command that corresponds to the <code>expression</code>.
     *
     * @param expression the input expression
     * @return the appropriate command
     */
    public static Commands getCommand(final String expression) {
        Commands result = Commands.UNKNOWN_COMMAND;
        for (Commands command : Commands.values()) {
            if (StringMatcher.findSubstring(expression, command.getRegex(), true)) {
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
    public static List<Command> getUserCommands() {
        List<Command> result = new ArrayList<Command>();
        for (Commands command : Commands.values()) {
            if (command.type == Type.USER) {
                result.add(new Command(command.name, command.description));
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