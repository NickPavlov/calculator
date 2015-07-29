package com.sysgears.application.parser.operations;

/**
 * The <code>Priority</code> class provides functionality to work with a priorities.
 * The top priority has index "0". The lowest priority has the largest index.
 */
public enum Priority {

    TOP(0),
    MEDIUM(1),
    LOW(2);

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
    private Priority(final int priority) {
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