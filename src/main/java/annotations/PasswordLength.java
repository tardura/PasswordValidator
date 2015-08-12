package annotations;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Size;
import java.lang.annotation.*;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.FIELD;

@Size(min= PasswordLength.DEFAULT_MIN_LENGTH, max= PasswordLength.DEFAULT_MAX_LENGTH)
@Documented
@Constraint(validatedBy={})
@Target({ANNOTATION_TYPE, METHOD, PARAMETER, FIELD, LOCAL_VARIABLE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordLength {
    String LENGTH_ERROR_MESSAGE = "Password does not fall within length constraints.";
    int DEFAULT_MIN_LENGTH = 3;
    int DEFAULT_MAX_LENGTH = 12;

    @OverridesAttribute(constraint=Size.class, name="min")
    int min() default DEFAULT_MIN_LENGTH;

    @OverridesAttribute(constraint=Size.class, name="max")
    int max() default DEFAULT_MAX_LENGTH;

    String message() default LENGTH_ERROR_MESSAGE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
