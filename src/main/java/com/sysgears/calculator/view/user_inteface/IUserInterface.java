package com.sysgears.calculator.view.user_inteface;

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
     * @throws IOException
     */
    public void sendMessage(final String message) throws IOException;

    /**
     * Should receive a message from the user.
     *
     * @return string
     * @throws IOException
     */
    public String read() throws IOException;

    /**
     * Should return an input stream.
     *
     * @return input stream
     */
    public InputStream getInputStream();

    /**
     * Should return an output stream.
     *
     * @return output stream
     */
    public OutputStream getOutputStream();

    /**
     * Should return a charset name.
     *
     * @return string
     */
    public String getCharsetName();
}