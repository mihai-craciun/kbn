package com.mihaicraciun.kbn.kbn.misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ErrorHandler {
    public static Map<String, List<String>> convertBindingResultErrorsToMap(BindingResult bindingResult) {
        Map<String, List<String>> errorsMap = new HashMap<>();

        if (bindingResult.hasErrors()) {
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                if (objectError instanceof FieldError) {
                    FieldError fieldError = (FieldError) objectError;
                    String field = fieldError.getField();
                    String errorMessage = fieldError.getDefaultMessage();
                    if (!errorsMap.containsKey(field))
                        errorsMap.put(field, new ArrayList<String>());
                    errorsMap.get(field).add(errorMessage);
                }
            }
        }

        return errorsMap;
    }
}