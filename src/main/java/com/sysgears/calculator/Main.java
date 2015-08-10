package com.sysgears.calculator;

import com.sysgears.calculator.model.history.History;
import com.sysgears.calculator.model.history.IHistory;
import com.sysgears.calculator.model.parser.IMathParser;
import com.sysgears.calculator.model.parser.MathParser;
import com.sysgears.calculator.view.userinteface.IUserInterface;
import com.sysgears.calculator.view.userinteface.UserInterface;

/**
 * The <code>Main</code> class creates the necessary objects for calculator work and passes control to the service.
 */
public class Main {

    /**
     * Launches service to manage calculator.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        try {
            final IMathParser calculator = new MathParser();
            final IUserInterface ui = new UserInterface(System.in, System.out);
            final IHistory history = new History();

            new Service(calculator, ui, history).start();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}