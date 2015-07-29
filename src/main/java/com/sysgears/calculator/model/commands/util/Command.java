package com.sysgears.calculator.model.commands.util;

/**
 * The <code>Command</code> class provides functionality to work with commands.
 */
public class Command {

    /**
     * Command name.
     */
    private final String name;

    /**
     * Command description.
     */
    private final String description;

    /**
     * Creates the <code>Command</code> object specified by name and description.
     *
     * @param name        command name
     * @param description command description
     */
    public Command(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Returns a command name.
     *
     * @return command name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a command description.
     *
     * @return command description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of a command.
     *
     * @return string
     */
    @Override
    public String toString() {
        return name + " : " + description;
    }
}