package com.combination.rest.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PhoneCombinationServiecImpl implements PhoneCombinationServiceI {

    private List<String> output;

    private Map<String, String> phone = new HashMap<String, String>() {{
        put("1", "1");
        put("2", "2ABC");
        put("3", "3DEF");
        put("4", "4GHI");
        put("5", "5JKL");
        put("6", "6MNO");
        put("7", "7PQRS");
        put("8", "8TUV");
        put("9", "9WXYZ");
        put("0", "0");
    }};

    @Override
    public List<String> phoneCombinationCalc(String digits) {

        output = new ArrayList<>();

        if (digits.length() != 0) {
            backtrack("", digits, output);
        }
        System.out.println("output size: " + output.size());
        return output;
    }

    public void backtrack(String combination, String nextdigits, List<String> output) {
        if (nextdigits.length() == 0) {
            output.add(combination);
        } else {
            String digit = nextdigits.substring(0, 1);
            String letters = phone.get(digit);
            for (int i = 0; i < letters.length(); i++) {
                String letter = letters.substring(i, i + 1);

                backtrack(combination + letter, nextdigits.substring(1), output);
            }
        }
    }
}
