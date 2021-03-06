package com.sysgears.calculator.model.parser.operations.util;

/**
 * The <code>Priority</code> class provides functionality to work with a priorities.
 * The top priority has index "0". The lowest priority has the largest index.
 */
public enum Priority {

    TOP(0),
    HIGH(1),
    MEDIUM(2),
    LOW(3);

    /**
     * Priority index.
     */
    private final int index;

    /**
     * Returns a lowest priority.
     *
     * @return lowest priority
     */
    public static int getLowestPriority() {
        int lowestPriority = 0;
        for (Priority o : Priority.values()) {
            if (o.getIndex() > lowestPriority) {
                lowestPriority = o.getIndex();
            }
        }

        return lowestPriority;
    }

    /**
     * @param priority index index
     */
    Priority(final int priority) {
        this.index = priority;
    }

    /**
     * Returns priority index.
     *
     * @return priority index
     */
    public int getIndex() {
        return index;
    }
}