package annotations;

import validators.AlphaNumericValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;

@Documented
@Constraint(validatedBy={AlphaNumericValidator.class})
@Target({ANNOTATION_TYPE, METHOD, PARAMETER, FIELD, LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AlphaNumeric {
    boolean DEFAULT_ALPHA_NUMERICS_REQUIRED = true;
    String DEFAULT_NON_ALPHA_NUMERIC_ERROR_MESSAGE = "Only Alpha-Numerics Allowed";
    String message() default DEFAULT_NON_ALPHA_NUMERIC_ERROR_MESSAGE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    boolean required() default DEFAULT_ALPHA_NUMERICS_REQUIRED;
}
