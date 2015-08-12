package validators;

import annotations.Cycles;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class CycleValidator implements ConstraintValidator<Cycles, String> {
    private boolean cyclesAllowed;
    private static final String MATCH_ON_CYCLES_REGEX = "(?!(.+?)\\1).*";

    @Override
    public void initialize(Cycles cycles) {
        cyclesAllowed = cycles.allowed();
    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        if(thereIsNoString(string)) return false;
        return cyclesAllowed || Pattern.matches(MATCH_ON_CYCLES_REGEX, string);
    }

    public boolean thereIsNoString(String string){
        return string == null;
    }
}
