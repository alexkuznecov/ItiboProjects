package util.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FacesValidator("util.validator.LoginValidator")
public class LoginValidator implements Validator {

    private static final String NAME_PATTERN = "^[a-z0-9_-]{3,16}$";
    private Pattern pattern;
    private Matcher matcher;

    public LoginValidator() {
        pattern = Pattern.compile(NAME_PATTERN);
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        matcher = pattern.matcher(o.toString());

        if (!matcher.matches()) {
            FacesMessage msg = new FacesMessage("Неверный логин : 1) англ.буквы и цифры 2) символы _ и - 3) от 3 до 16 символов длина");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
