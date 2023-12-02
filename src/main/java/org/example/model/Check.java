package org.example.model;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

public class Check {
    private Integer id;
    private Activity[] toDoList;
    private LocalDateTime dateTime;

    public Check() {
        id = Id.get();
    }

    public Check(Map<String, String> todos) {
        id = Id.get();
        toDoList = new Activity[todos.size()];
        int index = 0;
        for (Map.Entry<String, String> entry : todos.entrySet()) {
            toDoList[index] = new Activity(entry.getKey(), entry.getValue());
            index++;
        }
        dateTime = LocalDateTime.now();
    }

    public void setToDoList(Activity[] toDoList) {
        this.toDoList = toDoList;
    }

    public Integer getId() {
        return id;
    }

    public Activity[] getToDoList() {
        return toDoList;
    }

    /**
     * Change the status to the next one for the given activity index.
     *
     * @param activityNo
     * @return <code>true</code> - if activity is done, otherwise <code>false</code>
     * @see com.javafee.java.lesson.lesson8.oop1.model.Activity.Status, {@link Activity}
     */

    public Boolean changeStatus(int activityNo) {
        for (int i = 0; i < toDoList.length; i++)
            if (i == activityNo)
                return toDoList[i].changeStatus(toDoList[i].getStatus().statusValue + 1) == Activity.Status.SUCCEEDED;
        return false;
    }

    /**
     * Check if the list is completed, meaning if all the activities are performed
     * and finished with SUCCEEDED status.
     *
     * @return <code>true</code> - if list completed successfully, otherwise <code>false</code>
     * @see com.javafee.java.lesson.lesson8.oop1.model.Activity.Status, {@link Activity}
     */
    public Boolean isTodoListCompleted() {
        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i].getStatus().statusValue == Activity.Status.SUCCEEDED.statusValue)
                return true;
        }
        return false;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "Check{" +
                "id=" + id +
                ", toDoList=" + Arrays.toString(toDoList) +
                ", dateTime=" + dateTime +
                '}';
    }
}
