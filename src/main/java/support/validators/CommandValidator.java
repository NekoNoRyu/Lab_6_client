package support.validators;

import commands.*;

import java.util.Arrays;

public class CommandValidator {
    private final String request;
    private final String helpOutput;
    public CommandValidator(String request) {
        this.request = request;
        HelpCommand help = new HelpCommand();
        this.helpOutput = help.getOutput();
    }
    public boolean run() {
        String[] splittedInput = request.split(" ");
        String commandName = splittedInput[0];
        if (commandIsValid(request)) {
            return true;
        } else {
            if (!request.isBlank()) {
                for (String i : helpOutput.split("\n")) {
                    String[] iSplitted = i.split(" ");
                    if (iSplitted[0].equals(commandName)) {
                        System.out.println("invalid command input");
                        System.out.println(i);
                        return false;
                    }
                }
                System.out.println(commandName + " " + "(command not found)");
                return false;
            }
            return false;
        }
    }

    public static boolean commandIsValid(String command) {
        String[] splittedCommand = command.split(" ");
        if (splittedCommand.length == 1) {
            return noArgsCommandIsValid(command);
        } else if (splittedCommand.length == 2) {
            try {
                Integer.parseInt(splittedCommand[1]);
                return intArgCommandIsValid(command);
            } catch (NumberFormatException e) {
                return strArgCommandIsValid(splittedCommand[0]);
            }
        } else {return false;}
    }
    private static boolean noArgsCommandIsValid(String command) {
        String[] noArgsCommands = new String[]{"help", "info", "show", "clear", "exit", "remove_lower"};
        if (Arrays.asList(noArgsCommands).contains(command)) {
            return true;
        } else {return false;}
    }
    private static boolean intArgCommandIsValid(String command) {
        String[] intArgsCommands = new String[]{"insert", "update", "remove_key", "replace_if_lower", "remove_greater_key", "filter_greater_than_students_count"};
        String[] splittedCommand = command.split(" ");
        if (Arrays.asList(intArgsCommands).contains(splittedCommand[0])) {
            try {
                Integer.parseInt(splittedCommand[1]);
                return true;
            } catch (NumberFormatException e) {return false;}
        } else {return false;}
    }
    private static boolean strArgCommandIsValid(String command) {
        String[] strArgsCommands = new String[]{"execute_script", "remove_any_by_form_of_education", "count_by_form_of_education"};
        if (Arrays.asList(strArgsCommands).contains(command)) {
            return true;
        } else {return false;}
    }
}