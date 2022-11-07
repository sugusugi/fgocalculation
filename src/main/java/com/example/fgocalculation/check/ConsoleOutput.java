package com.example.fgocalculation.check;

public class ConsoleOutput {
    public static void checkValue(Object ... values) {
        String message = "";
        for(Object value: values) {
            message += value;
        }
        System.out.println(message);
    }
}
