package com.sysgears.application;

import com.sysgears.application.calculator.util.ICalculator;
import com.sysgears.application.commands.Commands;
import com.sysgears.application.converter.Converter;
import com.sysgears.application.converter.PrettyText;
import com.sysgears.application.history.History;
import com.sysgears.application.userinteface.UserInterface;

import java.io.IOException;

/**
 * The Service class performs management of the main parts of the application.
 */
public class Service {

    /**
     * Terminal width.
     */
    public static final int TERMINAL_WIDTH = 80;

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
        Commands command = null;
        try {
            while (command != Commands.EXIT) {
                ui.sendMessage(PrettyText.createSeparator("", TERMINAL_WIDTH) + "\n");
                message = ui.read();
                command = Commands.parse(message);
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
    private void execute(final Commands command, final String message) throws IOException {
        final String beforeSign = "(*) ";
        final String afterSign = "\n";
        switch (command) {
            case EVALUATE:
                String value = calculator.calculate(message);
                history.addRecord(Converter.removeSpaces(message) + "=" + value);
                ui.sendMessage("= " + value + "\n");
                break;
            case EXIT:
                ui.sendMessage("Goodbye!");
                break;
            case HISTORY:
                ui.sendMessage(PrettyText.createSeparator("History", TERMINAL_WIDTH) + "\n");
                ui.sendMessage(PrettyText.createString(history.getHistory(), beforeSign, afterSign));
                break;
            case HISTORY_UNIQUE:
                ui.sendMessage(PrettyText.createSeparator("Unique history", TERMINAL_WIDTH) + "\n");
                ui.sendMessage(PrettyText.createString(history.getUniqueHistory(), beforeSign, afterSign));
                break;
            case HELP:
            case UNKNOWN_COMMAND:
                ui.sendMessage(PrettyText.createSeparator("Help", TERMINAL_WIDTH) + "\n");
                ui.sendMessage(PrettyText.createString(Commands.getCommandsList(), beforeSign, afterSign));
                break;
        }
    }
}