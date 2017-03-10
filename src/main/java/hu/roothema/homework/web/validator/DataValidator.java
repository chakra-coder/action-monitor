package hu.roothema.homework.web.validator;

import hu.roothema.homework.exception.ValidationException;
import hu.roothema.homework.model.Data;
import org.springframework.stereotype.Component;

@Component
public class DataValidator {

    public void validate(Data data) {
        if (null == data) {
            throw new ValidationException("Null data cannot be stored");
        }

        if (null == data.getData() || "".equals(data.getData())) {
            throw new ValidationException("Empty data cannot be stored");
        }
    }
}
