package org.example.resume.constraints.validator;

import org.example.resume.constraints.annotation.EnglishLanguage;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnglishLanguageConstraintValidator implements ConstraintValidator<EnglishLanguage, String> {
    private static final String SPECIAL_SYMBOLS = "~#$%^&*-+=_\\|/@`!'\";:><,.?{}";
    private static final String PUNCTUATIONS = ".,?!-:()'\"[]{}; \t\n";
    private static final String NUMBERS = "0123456789";
    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private boolean withNumbers;
    private boolean withPunctuations;
    private boolean withSpecialSymbols;

    @Override
    public void initialize(EnglishLanguage constraintAnnotation) {
        this.withNumbers = constraintAnnotation.withNumbers();
        this.withPunctuations = constraintAnnotation.withPunctuations();
        this.withSpecialSymbols = constraintAnnotation.withSpecialSymbols();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;
        StringBuilder validationTemplate = new StringBuilder(LETTERS);
        if (withNumbers) validationTemplate.append(NUMBERS);
        if (withPunctuations) validationTemplate.append(PUNCTUATIONS);
        if (withSpecialSymbols) validationTemplate.append(SPECIAL_SYMBOLS);
        String template = validationTemplate.toString();
        for (int i = 0; i < value.length(); i++)
            if (template.indexOf(value.charAt(i)) == -1) return false;
        return true;
    }
}
