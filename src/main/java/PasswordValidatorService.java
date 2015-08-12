import annotations.ValidPassword;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;

@Service
@Validated
public class PasswordValidatorService {

    public boolean passwordValid(@ValidPassword String password) throws ConstraintViolationException{
        return true;
    }
}
