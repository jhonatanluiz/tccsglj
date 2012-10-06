/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcc.sglj.app;

import java.util.Set;
import java.lang.reflect.Type;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.ValidationException;


/**
 *
 * @author jhonatan L S Santos
 */
public abstract class App<T> {

    private Class classType;
    private static Validator validator;
    private String errorMessage;

    private void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        Type tipo = (Type) this.getClass().getGenericSuperclass();
        this.setClassType((Class) tipo.getClass());
    }

    public int isValid(T u) {
        try {
            this.setUp();
            Set<ConstraintViolation<T>> constraintViolations = validator.validate(u);
            if (!constraintViolations.isEmpty()) {
                setErrorMessage(constraintViolations.iterator().next().getMessage());
            }
            return constraintViolations.size();
        } catch (Exception e) {
            throw new ValidationException(e);
        }
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
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
