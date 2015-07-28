package com.sysgears.application;

import com.sysgears.application.calculator.util.ICalculator;
import com.sysgears.application.commands.Command;
import com.sysgears.application.converter.PrettyPrintingList;
import com.sysgears.application.history.History;
import com.sysgears.application.userinteface.UserInterface;

import java.io.IOException;

/**
 * The Service class performs management of the main parts of the application.
 */
public class Service {

    /**
     * Calculator, used to calculate mathematical expressions.
     */
    private final ICalculator calculator;

    /**
     * User interface, used to interact with the user.
     */
    private final UserInterface ui;

    /**
     * History, used for storing the history of calculations.
     */
    private final History history;

    /**
     * Constructs The Service object. Directly implements the management of the application.
     */
    public Service(final ICalculator calculator, final UserInterface ui, final History history) {
        this.calculator = calculator;
        this.ui = ui;
        this.history = history;
    }

    /**
     * Starts the service.
     */
    public void start() {
        String message;
        Command command = null;
        try {
            while (command != Command.EXIT) {
                ui.sendMessage("> ");
                message = ui.read();
                command = Command.parse(message);
                execute(command, message);
            }
        } catch (IOException e) {
            System.err.println("Connection is not available.");
            e.printStackTrace();
        }
    }

    /**
     * Performs an action, depending on the type of received command.
     *
     * @param command command type
     * @param message string
     * @throws IOException when Input/Output error
     */
    private void execute(final Command command, final String message) throws IOException {
        switch (command) {
            case EVALUATE:
                String value = calculator.calculate(message);
                history.addRecord(message + "=" + value);
                ui.sendMessage("= " + value + "\n");
                break;
            case EXIT:
                ui.sendMessage("Goodbye!");
                break;
            case HISTORY:
                ui.sendMessage(new PrettyPrintingList(history.getHistory()).createString("\n"));
                break;
            case HISTORY_UNIQUE:
                ui.sendMessage(new PrettyPrintingList(history.getUniqueHistory()).createString("\n"));
                break;
            case HELP:
            case UNKNOWN_COMMAND:
                ui.sendMessage(new PrettyPrintingList(Command.getCommandsList()).createString("\n"));
                break;
        }
    }
}