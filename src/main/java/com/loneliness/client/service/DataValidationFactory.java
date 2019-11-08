package com.loneliness.client.service;

import com.loneliness.entity.ProviderData;

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

    public Set<ConstraintViolation<ProviderData>> validate(ProviderData providerData) {
        return validator.validate(providerData);
    }
}
