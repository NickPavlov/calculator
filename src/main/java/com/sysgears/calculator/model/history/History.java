package com.sysgears.calculator.model.history;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * The <code>History</code> class keeps the history of the entered records.
 */
public class History implements IHistory {

    /**
     * The history.
     */
    private final List<String> history;

    /**
     * Creates the <code>History</code> object with the specified history storage.
     *
     * @param history the history storage
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
     * Adds the new record into the history.
     *
     * @param record the new record
     */
    public void addRecord(final String record) {
        history.add(record);
    }

    /**
     * Returns a history.
     * The real return type of the list - <code>ArrayList</code>.
     *
     * @return the history
     */
    public List<String> getHistory() {
        return new ArrayList<String>(history);
    }

    /**
     * Returns the unique history.
     * The real return type of the list - <code>ArrayList</code>.
     *
     * @return the unique history
     */
    public List<String> getUniqueHistory() {
        return new ArrayList<String>(new LinkedHashSet<String>(history));
    }
}