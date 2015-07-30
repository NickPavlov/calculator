package com.sysgears.calculator;

import com.sysgears.calculator.model.commands.Commands;
import com.sysgears.calculator.model.converter.Converter;
import com.sysgears.calculator.model.converter.PrettyText;
import com.sysgears.calculator.model.history.IHistory;
import com.sysgears.calculator.model.parser.IMathParser;
import com.sysgears.calculator.view.userinteface.IUserInterface;

import java.io.IOException;

/**
 * The <code>Service</code> class performs management of the main parts of the calculator.
 */
public class Service {

    /**
     * The math parser.
     * Used to parse mathematical expressions.
     */
    private final IMathParser mathParser;

    /**
     * The user interface.
     * Used to interact with the user.
     */
    private final IUserInterface ui;

    /**
     * The history.
     * Used for storing the history of calculations.
     */
    private final IHistory history;

    /**
     * Creates the <code>Service</code> object.
     * Directly implements the management of the calculator.
     *
     * @param mathParser a mathematical parser
     * @param ui         a user interface
     * @param history    a history
     */
    public Service(final IMathParser mathParser, final IUserInterface ui, final IHistory history) {
        this.mathParser = mathParser;
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
                ui.sendMessage(">");
                message = ui.receiveMessage();
                command = Commands.parse(message);
                execute(command, message);
            }
        } catch (IOException e) {
            System.err.println("Connection is not available.");
            e.printStackTrace();
        }
    }

    /**
     * Performs an action, depending on the type of the received command.
     *
     * @param command command type
     * @param message a message from user
     * @throws IOException when an I/O error has occurred
     */
    private void execute(final Commands command, final String message) throws IOException {
        final String beforeSign = "(*) ";
        final String afterSign = "\n";
        switch (command) {
            case EVALUATE:
                String value = mathParser.parse(message);
                history.addRecord(Converter.removeSpaces(message) + "=" + value);
                ui.sendMessage("= " + value + "\n\n");
                break;
            case EXIT:
                ui.sendMessage("\nGoodbye!\n");
                break;
            case HISTORY:
                ui.sendMessage("\n" + PrettyText.createString(history.getHistory(), beforeSign, afterSign) + "\n");
                break;
            case HISTORY_UNIQUE:
                ui.sendMessage("\n" + PrettyText.createString(history.getUniqueHistory(), beforeSign, afterSign)
                        + "\n");
                break;
            case HELP:
            case UNKNOWN_COMMAND:
                ui.sendMessage("\n" + PrettyText.createString(Commands.getCommandsList(), beforeSign, afterSign)
                        + "\n");
                break;
        }
    }
}