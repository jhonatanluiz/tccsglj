/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.sglj.dao;

import java.util.Set;
import java.lang.reflect.Type;

import javax.validation.Validator;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.ValidationException;
import javax.validation.ConstraintViolation;

import tcc.sglj.util.SystemMessage;

/**
 *
 * @author jhonatan
 */
public class ValidatorEntity<T> {

    private Class classType;
    private static Validator validator;

    private void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        Type tipo = (Type) this.getClass().getGenericSuperclass();
        this.setClassType((Class) tipo.getClass());
    }

    public void isValid(T u) throws ValidationException {

        setUp();

        Set<ConstraintViolation<T>> constraintViolations = validator.validate(u);
        if (!constraintViolations.isEmpty()) {
            StringBuilder exception = new StringBuilder();
            for (ConstraintViolation violation : constraintViolations) {
                exception.append(SystemMessage.get(violation.getMessage()));
                exception.append("<br>");
            }
            throw new ValidationException(exception.toString());
        }
    }

    /**
     * @return the classType
     */
    public Class getClassType() {
        return classType;
    }

    /**
     * @param classType the classType to set
     */
    public void setClassType(Class classType) {
        this.classType = classType;
    }
}
