package com.loneliness.client.service;

import com.loneliness.entity.CustomerData;
import com.loneliness.entity.OrderData;
import com.loneliness.entity.ProviderData;
import com.loneliness.entity.user.UserData;
import com.loneliness.entity.user.UserPrivateData;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class DataValidationFactory {
    private static final DataValidationFactory dataValidationFactory = new DataValidationFactory();
    private Validator validator ;

    private DataValidationFactory(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();

    }
    public static DataValidationFactory getValidatorFactory() {
        return dataValidationFactory;
    }

    public Set<ConstraintViolation<ProviderData>> validate(ProviderData data) {
        return validator.validate(data);
    }

    public Set<ConstraintViolation<OrderData>> validate(OrderData data) {
        return validator.validate(data);
    }

    public Set<ConstraintViolation<UserPrivateData>> validate(UserPrivateData data) {
        return validator.validate(data);
    }

    public Set<ConstraintViolation<UserData>> validate(UserData data) {
        return validator.validate(data);
    }

    public Set<ConstraintViolation<CustomerData>> validate(CustomerData data) {
        return validator.validate(data);
    }

}
