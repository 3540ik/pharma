package com.Pharma.Utiles;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        List<String> numbers = Arrays.asList
                ("imran","zain","arhaan","ayhaan","ayesha");
        List<String> newNumbers = numbers.stream().map(x->x.toUpperCase()).collect(Collectors.toList());
        System.out.println(newNumbers);
    }
}
