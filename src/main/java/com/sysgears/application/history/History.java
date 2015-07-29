package com.sysgears.application.history;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * The <code>History</code> class keeps the history of the entered expressions.
 */
public class History {

    /**
     * History.
     */
    private final List<String> history = new ArrayList<String>();

    /**
     * Adds new expression into the history.
     *
     * @param record new record
     */
    public void addRecord(final String record) {
        history.add(record);
    }

    /**
     * Returns the history.
     *
     * @return history
     */
    public List<String> getHistory() {
        return history;
    }

    /**
     * Returns the unique history.
     *
     * @return unique history
     */
    public List<String> getUniqueHistory() {
        return new ArrayList<String>(new LinkedHashSet<String>(history));
    }
}