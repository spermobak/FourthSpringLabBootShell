package com.bismus.springlab.appMenu;

import com.bismus.springlab.service.LanguageService;
import com.bismus.springlab.service.MessageService;
import com.bismus.springlab.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;


@ShellComponent
@RequiredArgsConstructor
public class AppMenu {
    private final PersonService personService;
    private final LanguageService languageService;
    private final MessageService messageService;

    private final Scanner scanner = new Scanner(System.in);

    private ResourceBundle message = ResourceBundle.getBundle("interfaceLanguage", new Locale("en"));


    @ShellMethod("Find all persons")
    public void findAll() {
        if (personService.findAll().isEmpty()) {
            messageService.printInterfaceMessage(message, "notFoundPersons");
        }
        messageService.printInterfaceMessage(message, "allPerson");
        messageService.printResultRequestMessage(personService.findAll());
    }

    @ShellMethod("Find person by id")
    public void findById() {
        messageService.printInterfaceMessage(message, "findById");
        int id = scanner.nextInt();

        if (personService.findById(id) == null) {
            messageService.printInterfaceMessage(message, "notFoundPerson");
        } else {
            messageService.printInterfaceMessage(message, "personById");
            messageService.printResultRequestMessage(personService.findById(id));
        }
    }

    @ShellMethod("Find person by name")
    public void findByName() {
        messageService.printInterfaceMessage(message, "findByName");
        var name = scanner.next();

        if (personService.findByName(name) == null) {
            messageService.printInterfaceMessage(message, "notFoundPerson");
        } else {
            messageService.printInterfaceMessage(message, "personByName");
            messageService.printResultRequestMessage(personService.findByName(name));
        }
    }

    @ShellMethod("Change language")
    public void changeLanguage() {
        messageService.printInterfaceMessage(message, "changeLanguage");
        languageService.printLanguageKey();
        var lang = scanner.next();
        message = languageService.setInterfaceLanguage(lang, message);
    }


}
