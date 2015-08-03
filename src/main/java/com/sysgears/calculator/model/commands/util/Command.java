package com.sysgears.calculator.model.commands.util;

/**
 * The <code>Operation</code> class provides functionality to work with commands.
 */
public class Command {

    /**
     * The operation name.
     */
    private final String name;

    /**
     * The operation description.
     */
    private final String description;

    /**
     * The metaVar parameter.
     */
    private final String metaVar;

    /**
     * Creates the <code>Operation</code> object specified by name and description.
     *
     * @param name        command name
     * @param description command description
     */
    public Command(final String name, final String description, final String metaVar) {
        this.name = name;
        this.description = description;
        this.metaVar = metaVar;
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
     * Returns the metaVar parameter.
     *
     * @return the metaVar parameter
     */
    public String getMetaVar() {
        return metaVar;
    }

    /**
     * Returns a string representation of a command.
     *
     * @return string
     */
    @Override
    public String toString() {
        return name + " " + metaVar + " : " + description;
    }
}