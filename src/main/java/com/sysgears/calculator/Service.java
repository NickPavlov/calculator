package com.sysgears.calculator;

import com.sysgears.calculator.model.commands.Commands;
import com.sysgears.calculator.model.converter.Converter;
import com.sysgears.calculator.model.converter.PrettyText;
import com.sysgears.calculator.model.history.History;
import com.sysgears.calculator.model.parser.util.IMathParser;
import com.sysgears.calculator.view.user_inteface.UserInterface;

import java.io.IOException;

/**
 * The Service class performs management of the main parts of the calculator.
 */
public class Service {

    /**
     * Terminal width.
     */
    //public static final int DEFAULT_TERMINAL_WIDTH = 80;

    /**
     * MathParser, used to parse mathematical expressions.
     */
    private final IMathParser mathParser;

    /**
     * User interface, used to interact with the user.
     */
    private final UserInterface ui;

    /**
     * History, used for storing the history of calculations.
     */
    private final History history;

    /**
     * Constructs The Service object. Directly implements the management of the calculator.
     *
     * @param mathParser mathematical parser
     * @param ui user interface
     * @param history history keeper
     */
    public Service(final IMathParser mathParser, final UserInterface ui, final History history) {
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
                String value = mathParser.parse(message);
                history.addRecord(Converter.removeSpaces(message) + "=" + value);
                ui.sendMessage("= " + value + "\n\n");
                break;
            case EXIT:
                ui.sendMessage("\nGoodbye!\n");
                //ui.sendMessage(PrettyText.createSeparator("", DEFAULT_TERMINAL_WIDTH) + "\n");
                break;
            case HISTORY:
                //ui.sendMessage(PrettyText.createSeparator("History", DEFAULT_TERMINAL_WIDTH) + "\n");
                ui.sendMessage("\n" + PrettyText.createString(history.getHistory(), beforeSign, afterSign) + "\n");
                break;
            case HISTORY_UNIQUE:
                //ui.sendMessage(PrettyText.createSeparator("Unique history", DEFAULT_TERMINAL_WIDTH) + "\n");
                ui.sendMessage("\n" + PrettyText.createString(history.getUniqueHistory(), beforeSign, afterSign)
                        + "\n");
                break;
            case HELP:
            case UNKNOWN_COMMAND:
                //ui.sendMessage(PrettyText.createSeparator("Help", DEFAULT_TERMINAL_WIDTH) + "\n");
                ui.sendMessage("\n" + PrettyText.createString(Commands.getCommandsList(), beforeSign, afterSign)
                        + "\n");
                break;
        }
    }
}