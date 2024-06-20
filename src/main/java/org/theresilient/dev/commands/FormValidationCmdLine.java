package org.theresilient.dev.commands;

import org.apache.commons.validator.GenericValidator;
import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;
import org.theresilient.dev.models.User;
import org.theresilient.dev.security.input.InputSanitizer;
import org.theresilient.dev.security.validators.FormValidator;
import org.theresilient.dev.shorcuts.CMD;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class FormValidationCmdLine {
    public static void run(Scanner scanner) {
        CMD.printLn("Validation des informations en entrée : cas d'un formulaire d'inscription\n\n");
        User user = new User();

        CMD.print("Entrez votre nom : ");
        String name = scanner.nextLine();
        if (GenericValidator.minLength(name, 2)) {
            user.setName(InputSanitizer.sanitize(name));
        } else {
            CMD.printLn("Nom invalide");
            return;
        }

        CMD.print("Entrez votre email : ");
        String email = scanner.nextLine();
        if (EmailValidator.getInstance().isValid(email)) {
            user.setEmail(InputSanitizer.sanitize(email));
        } else {
            CMD.printLn("Email invalide");
            return;
        }

        CMD.print("Entrez votre numéro de téléphone (+229 XX XXX XXX) : ");
        String phoneNumber = scanner.nextLine();
        if (new RegexValidator("^\\+229 \\d{2} \\d{3} \\d{3}$").isValid(phoneNumber)) {
            user.setPhoneNumber(InputSanitizer.sanitize(phoneNumber));
        } else {
            CMD.printLn("Numéro de téléphone invalide");
            return;
        }

        CMD.print("Entrez votre date de naissance : ");
        String birthDay = scanner.nextLine();
        if (DateValidator.getInstance().isValid(birthDay, "dd/MM/yyyy")) {
            final var birthDate = LocalDate.parse(birthDay, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            user.setBirthDate(birthDate);
            int age = (LocalDate.now().getYear() - birthDate.getYear());
            user.setAge(Integer.toString(age));
        } else {
            CMD.printLn("Date de naissance invalide");
            return;
        }

        CMD.printLn("\n\nVos informations entrées et nettoyées : ");
        CMD.printLn("\tNom : " + user.getName());
        CMD.printLn("\tEmail : " + user.getEmail());
        CMD.printLn("\tNuméro de Téléphone : " + user.getPhoneNumber());
        CMD.printLn("\tDate de naissance : " + user.getBirthDate());
        CMD.printLn("\tAge : " + user.getAge());
    }
}
