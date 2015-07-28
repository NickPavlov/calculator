package com.sysgears.application.history;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * The History class keeps the history of the entered expressions.
 */
public class History {

    /**
     * The expressions that have been calculated.
     */
    private final List<String> history = new ArrayList<String>();

    /**
     * Adds new expression into history database.
     *
     * @param record expression that will added into database
     */
    public void addRecord(final String record) {
        history.add(record);
    }

    /**
     * Returns the history of a calculated expressions.
     *
     * @return history
     */
    public List<String> getHistory() {
        return history;
    }

    /**
     * Returns the unique history of a calculated expressions.
     *
     * @return unique history
     */
    public List<String> getUniqueHistory() {
        return new ArrayList<String>(new LinkedHashSet<String>(history));
    }
}
