package util.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FacesValidator("util.validator.NameAndSurnameValidator")
public class NameAndSurnameValidator implements Validator{

    private static final String NAME_PATTERN = "^[а-яА-Яa-zA-Z0-9]*$";
    private Pattern pattern;
    private Matcher matcher;

    public NameAndSurnameValidator() {
        pattern = Pattern.compile(NAME_PATTERN);
    }

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        matcher = pattern.matcher(o.toString());

        if (!matcher.matches()) {
            FacesMessage msg = new FacesMessage("Неверные символы (рус. и англ. буквы и цифры)");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
