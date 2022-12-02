package com.bismus.springlab.service;


import com.bismus.springlab.exception.FailedSplitLineException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

@Service
@Component
public class ReaderService {

    private final Scanner scanner;

    public ReaderService(InputStream is){
        this.scanner = new Scanner(is);
    }

    public int readID(){
        if (scanner.hasNextInt()){
            return Integer.parseInt(scanner.nextLine());
        } else throw new IllegalArgumentException();
    }

    public String readWord(){
        if (scanner.hasNext()){
            if (!isNumb(scanner.next())){
                return scanner.nextLine().strip();
            } else throw new IllegalArgumentException();
        } else throw new NoSuchElementException();
    }



    private String readString(){
        return scanner.nextLine().strip();
    }

    public String[] getParameters (){
        return parameterDistributor(splitLine(readString()));
    }

    private static boolean isNumb(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String[] splitLine(String line) {
        String[] parameters = line.split(" ");
        if (parameters.length != 2) {
            throw new FailedSplitLineException();
        }
        return parameters;
    }

    private String[] parameterDistributor(String[] parameters) {
        if (isNumb(parameters[0])){
            return new String[]{parameters[1],parameters[0]};
        }else if (isNumb(parameters[1])){
            return parameters;
        } else
            throw new IllegalArgumentException("The entered string cannot be divided into name and age");
    }

}
