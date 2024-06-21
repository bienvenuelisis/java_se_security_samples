package org.theresilient.dev.commands;

import org.theresilient.dev.connection.SQLQuery;
import org.theresilient.dev.shorcuts.CMD;

import java.util.Scanner;

public class SQLInjectionCmdLine {
    public static void run(Scanner scanner) {
        CMD.printLn("Injecter une requête SQL dans un formulaire : compromettre la confidentialité et l'intégrite des données");

        int menuChoice;

        do {
            CMD.printLn("\n\n\nQuelle action souhaitez vous effectuer ? \n");
            CMD.printLn("\t1. Recherchez un utilisateur");
            CMD.printLn("\t2. Se connecter à l'espace admin");

            CMD.print("\n\tAction : ");

            String choice = scanner.nextLine();

            try {
                menuChoice = Integer.parseInt(choice);
            } catch (NumberFormatException e) {
                menuChoice = 0;
            }

            switch (menuChoice) {
                case 0:
                    continue;
                case 1:
                    searchUser(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
            }
        } while (menuChoice != 0);


    }

    private static void searchUser(Scanner scanner) {
        CMD.print("\n\nEntrez le  nom recherché (abalo, amah) : ");
        String username = scanner.nextLine();

        var db = SQLQuery.getUser(username);
        CMD.printLn("\n\nRésultats sans fitrage du nom utilisateur : " + db);

        var dbSafe = SQLQuery.getUserSafe(username);
        CMD.printLn("Résultats avec fitrage du nom utilisateur : " + dbSafe);
    }

    private static void login(Scanner scanner) {
        CMD.print("\n\nEntrez votre nom d'utilisateur (abalo, amah) : ");
        String username = scanner.nextLine();

        CMD.print("\n\nEntrez votre mot de passe (afi, kwatcha) : ");
        String password = scanner.nextLine();

        var db = SQLQuery.logIn(username, password);
        CMD.printLn("\n\nUtilisateur connecté sans fitrage du nom utilisateur : " + db);

        var dbSafe = SQLQuery.logInSafe(username, password);
        CMD.printLn("Utilisateur connecté avec fitrage du nom utilisateur : " + dbSafe);
    }
}
