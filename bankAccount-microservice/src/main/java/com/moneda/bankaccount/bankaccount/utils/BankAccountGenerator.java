package com.moneda.bankaccount.common.bankAccount.utils;
import java.util.Random;
import java.util.stream.Collectors;

public class BankAccountGenerator {
    // Generador de alias aleatorio de 4 caracteres
    public static String generateAlias() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        return new Random().ints(4, 0, chars.length())
                .mapToObj(chars::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
    }

}
