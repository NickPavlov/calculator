package com.sysgears.calculator;

import com.sysgears.calculator.model.parser.MathParser;
import com.sysgears.calculator.model.history.History;
import com.sysgears.calculator.view.user_inteface.UserInterface;

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
            final MathParser calculator = new MathParser();
            final UserInterface ui = new UserInterface(System.in, System.out);
            final History history = new History();
            final Service service = new Service(calculator, ui, history);
            service.start();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
