package com.sysgears.calculator.view.userinteface;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * The <code>IUserInterface<code/> interface describes the behavior of the user interface objects.
 */
public interface IUserInterface {

    /**
     * Should send a massage to the user.
     *
     * @param message string
     * @throws IOException when an I/O error has occurred
     */
    public void sendMessage(final String message) throws IOException;

    /**
     * Should receive a message from the user.
     *
     * @return a message from the user
     * @throws IOException when an I/O error has occurred
     */
    public String receiveMessage() throws IOException;

    /**
     * Should return an input stream.
     *
     * @return an input stream
     */
    public InputStream getInputStream();

    /**
     * Should return an output stream.
     *
     * @return an output stream
     */
    public OutputStream getOutputStream();

    /**
     * Should return a charset name.
     *
     * @return a charset name
     */
    public String getCharsetName();
}