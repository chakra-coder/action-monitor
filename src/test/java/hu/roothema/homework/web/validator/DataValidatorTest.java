package hu.roothema.homework.web.validator;

import hu.roothema.homework.exception.ValidationException;
import hu.roothema.homework.model.Data;
import org.junit.Test;

public class DataValidatorTest {

    private DataValidator dataValidator = new DataValidator();

    @Test(expected = ValidationException.class)
    public void testEmptyDataValidation() throws Exception {
        dataValidator.validate(new Data());
    }

    @Test
    public void testValidateDataForStoring() throws Exception {
        dataValidator.validate(new Data("sample"));
    }

}