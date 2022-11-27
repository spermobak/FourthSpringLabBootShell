package com.bismus.springlab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@Service
@RequiredArgsConstructor
public class LanguageService {

    private final MessageService messageService;


  private static final Map<String, ResourceBundle> languageMap = Map.of
          (
                  "en", ResourceBundle.getBundle("interfaceLanguage", new Locale("en")),
                  "fr", ResourceBundle.getBundle("interfaceLanguage", new Locale("fr"))
          );

    public ResourceBundle setInterfaceLanguage(String language, ResourceBundle message) {

        if (languageMap.containsKey(language)) {
            messageService.printInterfaceMessage(languageMap.get(language), "setLanguageSuccess");
            return languageMap.get(language);
        }
        else {
            messageService.printInterfaceMessage(message, "noLanguageFound");
            return message;
        }
    }

    public void printLanguageKey(){
        for (final Map.Entry<?,?> entry : languageMap.entrySet()){
            System.out.println(entry.getKey());
        }
    }

    public ResourceBundle setDefaultInterfaceLanguage(){
        return languageMap.get("en");
    }
}
