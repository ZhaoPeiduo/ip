package duke;

/**
 * Represents a task with a description and a status.
 */
public class Task {
    private final String taskTitle;
    private boolean isDone;

    public Task(String taskTitle) {
        this.taskTitle = taskTitle;
        //When a task is first created, it is by default not completed.
        this.isDone = false;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    /**
     * Customize the string representation of a task object.
     *
     * @return string representation of a task in the form [][{X}] {description}
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + taskTitle;
        } else {
            return "[ ] " + taskTitle;
        }
    }
}
