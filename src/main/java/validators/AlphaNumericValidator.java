package validators;

import annotations.AlphaNumeric;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class AlphaNumericValidator implements ConstraintValidator<AlphaNumeric, String> {
    private boolean required;
    private static final String MATCH_ON_ALPHA_NUMERIC_REGEX = "^[0-9a-zA-Z]+$";

    @Override
    public void initialize(AlphaNumeric alphaNumeric) {
        required = alphaNumeric.required();
    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        if(string == null) return false;
        return required && Pattern.matches(MATCH_ON_ALPHA_NUMERIC_REGEX, string);
    }
}
