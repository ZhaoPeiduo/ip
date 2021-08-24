package duke;

import java.util.ArrayList;

/**
 * The ui for Parser that is in charge of displaying relevant messages to the user.
 * */
public class ParserUi extends Ui{

    /**
     * Remind the user that the parser cannot interpret the input command.
     * */
    public void cannotInterpretMessage() {
        System.out.println(formatMessage("OOPS!!! I'm sorry, but I don't know what that means :-(\n"));
    }

    /**
     * Show the user the list of events saved at .../data/record.
     * */
    public void printUserInputRecord(ArrayList<Task> userInputRecord) {
        if(userInputRecord.isEmpty()) {
            System.out.println(formatMessage("Ah oh, seems like nothing is added yet :( \n" +
                    getIndentation() + "Try to input something first! \n" ));
        } else {
            System.out.println("    ____________________________________________________________");
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < userInputRecord.size(); i++) {
                System.out.println("     " + (i + 1) + "." + userInputRecord.get(i));
            }
            System.out.println("    ____________________________________________________________");
        }
    }
}
