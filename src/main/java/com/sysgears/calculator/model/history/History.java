package com.sysgears.calculator.model.history;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * The <code>History</code> class keeps the history of the entered expressions.
 */
public class History implements IHistory {

    /**
     * History.
     */
    private final List<String> history;

    /**
     * Creates the <code>History</code> object with a specified history storage.
     *
     * @param history history storage
     */
    public History(final List<String> history) {
        this.history = history;
    }

    /**
     * Creates the <code>History</code> object with the <code>ArrayList</code> as a storage.
     */
    public History() {
        this(new ArrayList<String>());
    }

    /**
     * Adds new record into the history.
     *
     * @param record a new record
     */
    public void addRecord(final String record) {
        history.add(record);
    }

    /**
     * Returns a history.
     * The real return type of the list - <code>ArrayList</code>.
     *
     * @return a history
     */
    public List<String> getHistory() {
        return new ArrayList<String>(history);
    }

    /**
     * Returns a unique history.
     * The real eturn type of the list - <code>ArrayList</code>.
     *
     * @return a unique history
     */
    public List<String> getUniqueHistory() {
        return new ArrayList<String>(new LinkedHashSet<String>(history));
    }
}