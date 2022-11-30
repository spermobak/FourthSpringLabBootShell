package com.bismus.springlab.appMenu;

import com.bismus.springlab.exception.FailedSplitLineException;
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
        messageService.printInterfaceMessage(message, "successAllPerson");
        messageService.printResultRequestMessage(personService.findAll());
    }

    @ShellMethod("Find person by id")
    public void findById() {
        messageService.printInterfaceMessage(message, "findById");
        int id = scanner.nextInt();

        if (personService.findById(id) == null) {
            messageService.printInterfaceMessage(message, "notFoundPerson");
        } else {
            messageService.printInterfaceMessage(message, "successPersonById");
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
            messageService.printInterfaceMessage(message, "successPersonByName");
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

    @ShellMethod("Insert new person in database")
    public void insertNewPerson() {
        messageService.printInterfaceMessage(message, "addNewPerson");
        var line = scanner.nextLine();

        String[] parameters = parameterDistributor(splitLine(line));

        String name = parameters[0];
        int age = Integer.parseInt(parameters[1]);

        personService.addNewPerson(name, age);
        messageService.printInterfaceMessage(message,"successNewPerson");
    }

    @ShellMethod("Update person parameters")
    public void updatePerson(){
        messageService.printInterfaceMessage(message, "updatePersonParamWriteId");
        int id = scanner.nextInt();

        if (personService.findById(id) == null) {
            messageService.printInterfaceMessage(message, "notFoundPerson");
        } else {
            messageService.printInterfaceMessage(message, "updatePersonParamWriteNameAge");
            String line = scanner.nextLine();

            String[] parameters = parameterDistributor(splitLine(line));

            String name = parameters[0];
            int age = Integer.parseInt(parameters[1]);

            personService.updatePersonParam(id,name,age);
            messageService.printInterfaceMessage(message, "successUpdatedPerson");
        }
    }

    @ShellMethod("Delete person by id")
    public void deletePerson() {
        messageService.printInterfaceMessage(message, "deletePerson");
        int id = scanner.nextInt();

        if (personService.findById(id) == null) {
            messageService.printInterfaceMessage(message, "notFoundPerson");
        } else {
            personService.deletePerson(id);
            messageService.printInterfaceMessage(message, "successDeletedPerson");
        }
    }




    private static boolean isNumb(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String[] splitLine(String line){
        String[] parameters = line.split(" ");
        if (parameters.length != 2) {
            throw new FailedSplitLineException();
        }
        return parameters;
    }

    private String[] parameterDistributor(String[] parameters){
        if (isNumb(parameters[0])){
            return new String[]{parameters[1],parameters[0]};
        }else if (isNumb(parameters[1])){
            return parameters;
        } else
            throw new IllegalArgumentException("The entered string cannot be divided into name and age");
    }
}
