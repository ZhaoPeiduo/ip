package duke;

import java.util.ArrayList;

/**
 * Parse user's input, identify whether it is a valid command and calls relevant method(s) to execute the command.
 * @author Zhao Peiduo
 */

public class Parser {
    private final TaskList tasks;

    /**
     * The constructor for a Parser Object.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * This method checks if a String starting with delete is a delete command.
     *
     * @param userInput input from the user.
     * @return whether the user input can be taken as a delete command.
     */
    private boolean checkDeleteCommand(String userInput) {
        String copy = userInput.replace("delete", "");
        copy = copy.replaceAll("[0-9]", "");
        copy = copy.trim();
        return copy.isEmpty();
    }

    /**
     *  This method checks if a String starting with done is a done command.
     *
     * @param userInput input from the user.
     * @return whether the user input can be taken as a done command.
     **/
    private boolean checkDoneCommand(String userInput) {
        String copy = userInput.replace("done", "");
        copy = copy.replaceAll("[0-9]", "");
        copy = copy.trim();
        return copy.isEmpty();
    }

    /**
     * Parses the user's command and calls relevant method to execute the command.
     *
     * @param userInput the command from the user.
     * @return the response generated by executing user's command
     */
    public String parse(String userInput) {
        if (userInput.equals("list")) {
            return printList();
        } else if (userInput.startsWith("done")) {
            return printDoneMessage(userInput);
        } else if (userInput.equals("deleteAll")) {
            return tasks.deleteAll(tasks.getStorage().getUserInputRecords());
        } else if (userInput.startsWith("delete")) {
            return printDeleteMessage(userInput);
        } else if (userInput.startsWith("save ")) {
            return tasks.getStorage().save(userInput);
        } else if (userInput.startsWith("load ")) {
            return tasks.getStorage().load(userInput);
        } else if (userInput.startsWith("find")) {
            return tasks.search(userInput, tasks.getStorage().getUserInputRecords());
        } else if (userInput.equals("help")) {
            return printHelpMessage();
        } else if (userInput.startsWith("todo ")) {
            return tasks.addToDo(userInput, tasks.getStorage().getUserInputRecords());
        } else if (userInput.startsWith("deadline ") && userInput.contains("/by")) {
            return tasks.addDeadline(userInput, tasks.getStorage().getUserInputRecords());
        } else if (userInput.startsWith("event ") && userInput.contains("/at")) {
            return tasks.addEvent(userInput, tasks.getStorage().getUserInputRecords());
        } else if (userInput.startsWith("update ") && userInput.contains("/to")) {
            return tasks.updateTask(userInput);
        } else {
            return "OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        }
    }

    private String printHelpMessage() {
        StringBuilder builder = new StringBuilder();
        builder.append("todo <description>\n").append("deadline <description>/by <time in format yyyy-mm-dd>\n")
                .append("event <description>/at <time in format yyyy-mm-dd>\n").append("save <directory>\n")
                .append("load <directory>\n").append("done <number>\n").append("delete <number>\n")
                .append("deleteAll\n").append("find <keyword>\n")
                .append("update <index> /to <Task in Todo/Deadline/Event format>");
        return builder.toString();
    }

    private String printList() {
        ArrayList<Task> userInputRecords = tasks.getStorage().getUserInputRecords();
        if (userInputRecords.isEmpty()) {
            return "Ah oh, seems like nothing is added yet :( \n" + "Try to input something first! \n";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Here are the tasks in your list:\n");
            for (int i = 0; i < userInputRecords.size(); i++) {
                stringBuilder.append("  " + (i + 1) + "." + userInputRecords.get(i) + "\n");
            }
            return stringBuilder.toString();
        }
    }

    private String printDeleteMessage(String userInput) {
        if (checkDeleteCommand(userInput)) {
            return tasks.delete(userInput, tasks.getStorage().getUserInputRecords());
        } else {
            return "OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        }
    }

    private String printDoneMessage(String userInput) {
        if (checkDoneCommand(userInput)) {
            return tasks.markAsDone(userInput, tasks.getStorage().getUserInputRecords());
        } else {
            return "OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        }
    }
}
