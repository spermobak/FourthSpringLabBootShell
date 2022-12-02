package com.bismus.springlab.appMenu;

import com.bismus.springlab.service.LanguageService;
import com.bismus.springlab.service.MessageService;
import com.bismus.springlab.service.PersonService;
import com.bismus.springlab.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;


import java.util.Locale;
import java.util.ResourceBundle;


@ShellComponent
@RequiredArgsConstructor
public class AppMenu {
    private final PersonService personService;
    private final LanguageService languageService;
    private final MessageService messageService;

    private final ReaderService readerService;

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
        int id = readerService.readID();

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
        var name = readerService.readWord();

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
        var lang = readerService.readWord();
        message = languageService.setInterfaceLanguage(lang, message);
    }

    @ShellMethod("Insert new person in database")
    public void insertNewPerson() {
        messageService.printInterfaceMessage(message, "addNewPerson");

        String[] parameters = readerService.getParameters();

        String name = parameters[0];
        int age = Integer.parseInt(parameters[1]);

        personService.addNewPerson(name, age);
        messageService.printInterfaceMessage(message,"successNewPerson");
    }

    @ShellMethod("Update person parameters")
    public void updatePerson(){
        messageService.printInterfaceMessage(message, "updatePersonParamWriteId");
        int id = readerService.readID();

        if (personService.findById(id) == null) {
            messageService.printInterfaceMessage(message, "notFoundPerson");
        } else {
            messageService.printInterfaceMessage(message, "updatePersonParamWriteNameAge");

            String[] parameters = readerService.getParameters();

            String name = parameters[0];
            int age = Integer.parseInt(parameters[1]);

            personService.updatePersonParam(id,name,age);
            messageService.printInterfaceMessage(message, "successUpdatedPerson");
        }
    }

    @ShellMethod("Delete person by id")
    public void deletePerson() {
        messageService.printInterfaceMessage(message, "deletePerson");
        int id = readerService.readID();

        if (personService.findById(id) == null) {
            messageService.printInterfaceMessage(message, "notFoundPerson");
        } else {
            personService.deletePerson(id);
            messageService.printInterfaceMessage(message, "successDeletedPerson");
        }
    }

}
