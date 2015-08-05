package com.sysgears.calculator.model.commands;

import com.sysgears.calculator.model.commands.util.Command;
import com.sysgears.calculator.model.commands.util.Type;
import com.sysgears.calculator.model.util.strings.StringMatcher;
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
    CALCULATE("calc", "Calculates the expression.", "<math_expr>", true, Type.USER),
    HISTORY("history", "Display the history of calculations.", Type.USER),
    HISTORY_UNIQUE("history unique", "Display all unique calculations.", Type.USER),
    OPERATIONS("operations", "Display the list of operations.", Type.USER),
    HELP("help", "Display a list of all available commands.", Type.USER),
    EXIT("exit", "Exit the program.", Type.USER),

    /**
     * System commands.
     */
    UNKNOWN_COMMAND("unknown command", "", Type.SYSTEM);

    /**
     * The command name.
     */
    private final String name;

    /**
     * The command type.
     */
    private final Type type;

    /**
     * The command description.
     */
    private final String description;

    /**
     * The command pattern
     */
    private final String regex;

    /**
     * The command metaVar parameter.
     */
    private final String metaVar;

    /**
     * @param name        the command name
     * @param type        the command type
     * @param metaVar     the command metaVar parameter
     * @param description the command description
     */
    Commands(final String name, final String description, final String metaVar, final boolean options, final Type type) {
        this.name = name;
        this.type = type;
        this.metaVar = metaVar;
        this.description = description;
        String regex;
        if (type == Type.USER) {
            regex = RegexCreator.createAtBeginning(RegexCreator.createWithSpaces(name));
            if (!options) {
                regex = RegexCreator.createAtEnd(regex);
            }

        } else {
            regex = name;
        }
        this.regex = regex;
    }

    /**
     * @param name        the command name
     * @param description the command type
     * @param type        the command description
     */
    Commands(String name, String description, final boolean options, final Type type) {
        this(name, description, "", options, type);
    }

    /**
     * @param name        the command name
     * @param description the command type
     * @param type        the command description
     */
    Commands(String name, String description, final Type type) {
        this(name, description, "", false, type);
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
                result.add(new Command(command.name, command.description, command.metaVar));
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

    /**
     * Returns the command metaVar parameter.
     *
     * @return the metaVar parameter
     */
    public String getMetaVar() {
        return metaVar;
    }
}