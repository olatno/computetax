package com.tax.util;

import com.tax.beans.Users;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created with IntelliJ IDEA.
 * User: Ola
 * Date: 24/05/15
 * Time: 11:52
 * To change this template use File | Settings | File Templates.
 */
public class InputValidation implements Validator {

    @Override
    public boolean supports(Class clazz) {
        //just validate the Users instances
        return Users.class.isAssignableFrom(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "income", "required.income", "Field name is required.");
    }
}
