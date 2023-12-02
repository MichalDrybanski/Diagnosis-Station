package org.example.model;

import java.util.Arrays;

public class Activity {
    /**
     * activity title
     **/
    private String name;
    /**
     * detailed info about the activity
     **/
    private String description;
    /**
     * status of completion, <code>Status.TODO</code> as default
     **/
    private Status status = Status.TODO;

    public Activity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Moves status to the next one. Internally the method is using
     * integer values assigned to each status and once called, then
     * is moving the status from the status that is represented with
     * value X to the status that is represented with value of X + 1.
     * The statuses sequence is as follows:
     * <ul>
     *     <li>TODO</li>
     *     <li>IN_PROGRESS</li>
     *     <li>SUCCEEDED</li>
     *     <li>FAILED</li>
     *     <li>UNKNOWN</li>
     * </ul>
     * Important - it's NOT possible to move status and skip the next one,
     * it means that f.eg. it's not possible to move status from IN_PROGRESS
     * to FAILED directly without iteration over SUCCEEDED.
     *
     * @return current status
     * @see Status
     */
    public Status changeStatus() {
        status = Status.byValue(status.statusValue + 1);
        return status;
    }

    /**
     * Moves status to the given one. Internally the method is using
     * provided integer value to movie the status from the status that
     * is represented with value X to the status that is represented
     * with provided value.
     * The statuses values are as follows:
     * <ul>
     *     <li>0 - TODO</li>
     *     <li>1 - IN_PROGRESS</li>
     *     <li>2 - SUCCEEDED</li>
     *     <li>3 - FAILED</li>
     *     <li>4 -UNKNOWN</li>
     * </ul>
     *
     * @return current status
     * @see Status
     */
    public Status changeStatus(int statusValue) { //TODO: method is not working as expect(o patronum)ed :(
        status = Status.byValue(statusValue);
        return status;
    }

    public Status getStatus() {
        return status;
    }

    public enum Status {
        TODO(0), IN_PROGRESS(1), SUCCEEDED(2), FAILED(3), UNKNOWN(4);

        int statusValue;

        Status(int statusValue) {
            this.statusValue = statusValue;
        }

        public static Status byValue(int statusValue) {
            return Arrays.stream(values()).filter(s -> s.statusValue == statusValue).findFirst().orElse(UNKNOWN);
        }
    }

    @Override
    public String toString() {
        return "Activity{" +
                "name='" + name + '\'' +
                '}';
    }
}
