package application;

import commands.Command;
import commands.HelpCommand;
import data.StudyGroup;
import support.*;
import support.validators.CommandValidator;

import java.io.IOException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("Press ctrl+D or type \"exit\" to exit program");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String request = scanner.nextLine();
            request = DeleteSpaces.delete(request);
            CommandValidator validator = new CommandValidator(request);
            if (validator.run()) {
                Command command = new Command();
                boolean initializingCompletedCorrectly = initializeCommandFields(command, request);
                if (!initializingCompletedCorrectly) {
                    System.out.println("The entry of the element was completed incorrectly, the command was not executed");
                    continue;
                }
                if (command.getType().equals("help")) { //команда help выполняется на клиенте
                    HelpCommand helpCommand = new HelpCommand();
                    helpCommand.execute();
                    continue;
                } else if (command.getType().equals("exit")) {
                    break;
                }
                byte[] serializedCommand = Command.serialize(command);
                try {
                    InteractionWithServer.send(serializedCommand);
                    String answer = InteractionWithServer.receive();
                    while (!answer.equals("endOfResponse")) { //метка endOfResponse означает, что сервер закончил отвечать клиенту
                        System.out.println(answer);
                        answer = InteractionWithServer.receive();
                    }
                } catch (IOException e) {
                    System.out.println("Server is not responding, try again later");
                }
            }
        }
    }
    private static boolean initializeCommandFields(Command command, String request) {
        String[] splittedRequest = request.split(" ");
        command.setType(splittedRequest[0]);
        if (splittedRequest.length > 1) {
            command.setArgument(splittedRequest[1]);
        }
        if (Command.requiresElement(splittedRequest[0])) {
            ElementReceiver elementReceiver = new ElementReceiver();
            StudyGroup element = elementReceiver.receive();
            if (elementReceiver.getExit() == 0) {
                command.setElement(element);
            } else {
                return false;
            }
        }
        return true;
    }
}