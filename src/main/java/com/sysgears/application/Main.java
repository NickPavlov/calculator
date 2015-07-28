package com.sysgears.application;

import com.sysgears.application.calculator.Calculator;
import com.sysgears.application.userinteface.UserInterface;
import com.sysgears.application.history.History;

/**
 * The <code>Main</code> class creates the necessary objects for application work and passes control to the service.
 */
public class Main {

    /**
     * Launches service to manage application.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        try {
            final Calculator calculator = new Calculator();
            final UserInterface ui = new UserInterface(System.in, System.out);
            final History history = new History();
            final Service service = new Service(calculator, ui, history);

            service.start();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
