package org.theresilient.dev;

import org.theresilient.dev.commands.FormValidationCmdLine;
import org.theresilient.dev.shorcuts.CMD;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CMD.printLn("*******************************************************************");

        int menuChoice;

        do {
            CMD.printLn("\n\n\nQuelle action souhaitez vous effectuer ? \n");
            CMD.printLn("\t1. Valider des entrées utilisateurs");

            CMD.print("\n\tAction : ");

            String choice = scanner.nextLine();

            CMD.printLn("\n\n");

            try {
                menuChoice = Integer.parseInt(choice);
            } catch (NumberFormatException e) {
                menuChoice = 0;
            }

            switch (menuChoice) {
                case 0:
                    continue;
                case 1:
                    FormValidationCmdLine.run(scanner);
                    break;
            }
        } while (menuChoice != 0);


    }
}