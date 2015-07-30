package com.sysgears.calculator.model.history;

import java.util.List;

/**
 * The <code>IHistory</code> interface defines the behavior of the history object.
 */
public interface IHistory {

    /**
     * Should add a new record into the history.
     *
     * @param record a new record
     */
    public void addRecord(final String record);

    /**
     * Should return a full list of records.
     *
     * @return all history
     */
    public List<String> getHistory();

    /**
     * Should return a list of records without repetition.
     *
     * @return a list of records without repetition
     */
    public List<String> getUniqueHistory();
}