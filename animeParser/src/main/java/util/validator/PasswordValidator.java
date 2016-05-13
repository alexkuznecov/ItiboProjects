package util.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FacesValidator("util.validator.PasswordValidator")
public class PasswordValidator implements Validator {

    private static final String NAME_PATTERN = "^[a-z0-9_-]{6,18}$";
    private Pattern pattern;
    private Matcher matcher;

    public PasswordValidator() {
        pattern = Pattern.compile(NAME_PATTERN);
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        matcher = pattern.matcher(o.toString());

        if (!matcher.matches()) {
            FacesMessage msg = new FacesMessage("Неправильный пароль : 1) англ.буквы и цифры 2) символы _ и - 3) от 6 до 18 символов длина");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
