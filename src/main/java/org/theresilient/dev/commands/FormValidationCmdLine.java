package org.theresilient.dev.commands;

import org.theresilient.dev.models.User;
import org.theresilient.dev.security.input.InputSanitizer;
import org.theresilient.dev.security.validators.FormValidator;
import org.theresilient.dev.shorcuts.CMD;

import java.util.Scanner;

public class FormValidationCmdLine {
    public static void run(Scanner scanner) {
        CMD.printLn("Validation des informations en entrée : cas d'un formulaire d'inscription\n\n");
        User user = new User();

        CMD.print("Entrez votre nom : ");
        String name = scanner.nextLine();
        if (FormValidator.isValidName(name)) {
            user.setName(InputSanitizer.sanitize(name));
        } else {
            CMD.printLn("Nom invalide");
            return;
        }

        CMD.print("Entrez votre email : ");
        String email = scanner.nextLine();
        if (FormValidator.isValidEmail(email)) {
            user.setEmail(InputSanitizer.sanitize(email));
        } else {
            CMD.printLn("Email invalide");
            return;
        }

        CMD.print("Entrez votre numéro de téléphone : ");
        String phoneNumber = scanner.nextLine();
        if (FormValidator.isValidPhoneNumber(phoneNumber)) {
            user.setPhoneNumber(InputSanitizer.sanitize(phoneNumber));
        } else {
            CMD.printLn("Numéro de téléphone invalide");
            return;
        }


        CMD.print("Entrez votre date de naissance : ");
        String birthDay = scanner.nextLine();
        if (FormValidator.isValidDate(birthDay, java.util.Optional.empty())) {
            user.setBirthDate(birthDay);
        } else {
            CMD.printLn("Date de naissance invalide");
            return;
        }

        CMD.printLn("Vos informations entrées et nettoyées");
        CMD.printLn("Nom : " + user.getName());
        CMD.printLn("Email : " + user.getEmail());
        CMD.printLn("Numéro de Téléphone : " + user.getPhoneNumber());
        CMD.printLn("Date de naissance : " + user.getBirthDate());
    }
}
