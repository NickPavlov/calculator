package com.sysgears.calculator.model.history;

import java.util.List;

/**
 * The <code>IHistory</code> interface defines the behavior of the history object.
 */
public interface IHistory {

    /**
     * Adds a new record into the history.
     *
     * @param record a new record
     */
    public void addRecord(final String record);

    /**
     * Returns a full list of records.
     *
     * @return all history
     */
    public List<String> getHistory();

    /**
     * Returns a list of records without repetition.
     *
     * @return a list of records without repetition
     */
    public List<String> getUniqueHistory();
}