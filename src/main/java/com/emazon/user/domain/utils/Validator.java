package com.emazon.user.domain.utils;

import com.emazon.user.domain.exeption.user.*;
import com.emazon.user.domain.model.Role;
import com.emazon.user.domain.model.User;
import com.emazon.user.domain.spi.IRolePersistencePort;
import com.emazon.user.domain.spi.IUserPersistencePort;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static com.emazon.user.domain.utils.DomainConstants.*;

public class Validator {

    private static final String[] TYPE_EXCEPTIONS = { "Required","Invalid" };

    private static final String REGEX_PHONE_NUMBER="^\\+?\\d{1,12}$";
    private static final String REGEX_ID_DOCUMENT="^\\d+$";
    private static final String REGEX_EMAIL="^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String REGEX_PASSWORD="^(?!.*password)(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&_])[A-Za-z\\d@$!%*?&_]{8,}$";
    private static final String REGEX_NAME_OR_LAST_NAME="^[A-Za-z\\s]{1,50}$";

    private Validator() {
    }

    private static final Map<String, Supplier<RuntimeException>> EXCEPTION_MAP = new HashMap<>();
    static {
        EXCEPTION_MAP.put("UserNameRequired", UserNameRequiredException::new);
        EXCEPTION_MAP.put("UserLastNameRequired", UserLastNameRequiredException::new);
        EXCEPTION_MAP.put("UserIdDocumentRequired", UserIdDocumentRequiredException::new);
        EXCEPTION_MAP.put("UserPhoneNumberRequired", UserPhoneNumberRequiredException::new);
        EXCEPTION_MAP.put("UserDateOfBirthRequired", UserDateOfBirthRequiredException::new);
        EXCEPTION_MAP.put("UserPasswordRequired", UserPasswordRequiredException::new);
        EXCEPTION_MAP.put("UserEmailRequired", UserEmailRequiredException::new);


        EXCEPTION_MAP.put("UserNameInvalid", UserNameInvalidException::new);
        EXCEPTION_MAP.put("UserLastNameInvalid", UserLastNameInvalidException::new);
        EXCEPTION_MAP.put("UserIdDocumentInvalid", UserIdDocumentInvalidException::new);
        EXCEPTION_MAP.put("UserPhoneNumberInvalid", UserPhoneNumberInvalidException::new);
        EXCEPTION_MAP.put("UserDateOfBirthInvalid", UserDateOfBirthInvalidException::new);
        EXCEPTION_MAP.put("UserPasswordInvalid", UserPasswordInvalidException::new);
        EXCEPTION_MAP.put("UserEmailInvalid", UserEmailInvalidException::new);
    }

    public static void validateUserProperties(User user, IRolePersistencePort rolePersistencePort){
        validateNotNullNotEmptyAndMatchesRegex(user.getName(),REGEX_NAME_OR_LAST_NAME,PROPERTY_NAME);
        validateNotNullNotEmptyAndMatchesRegex(user.getLastName(),REGEX_NAME_OR_LAST_NAME,PROPERTY_LAST_NAME);
        validateNotNullNotEmptyAndMatchesRegex(user.getIdDocument(),REGEX_ID_DOCUMENT,PROPERTY_ID_DOCUMENT);
        validateNotNullNotEmptyAndMatchesRegex(user.getPhoneNumber(),REGEX_PHONE_NUMBER,PROPERTY_PHONE_NUMBER);
        validateNotNullNotEmptyAndMatchesRegex(user.getPassword(),REGEX_PASSWORD,PROPERTY_PASSWORD);
        validateNotNullNotEmptyAndMatchesRegex(user.getEmail(),REGEX_EMAIL,PROPERTY_EMAIL);
        validateUserAge(user.getDateOfBirth());
        validateUserRoll(user.getRole(),rolePersistencePort);

    }

    private static void validateUserRoll(Role role, IRolePersistencePort rolePersistencePort) {
            Role existRole = rolePersistencePort.findByRoleEnum(role.getRoleEnum());
            role.setId(
                    existRole == null ? rolePersistencePort.saveRole(role) : existRole.getId()
            );
    }
    public static void validateEmailIsAlreadyInUse(User user, IUserPersistencePort userPersistencePort) {
        if(userPersistencePort.isEmailAlreadyInUse(user.getEmail())){
            throw new UserEmailAlreadyExistException();
        }
    }
    public static void validateIdDocumentIsAlreadyInUse(User user, IUserPersistencePort userPersistencePort) {
        if(userPersistencePort.isIdDocumentInUse(user.getIdDocument())){
            throw new UserIdDocumentAlreadyExistException();
        }
    }

    private static void validateNotNullNotEmptyAndMatchesRegex(String text,String regex, String property){
        validateIsNotNullOrEmpty(text,property);
        validateUsingRegex(text,regex,property);
    }
    private static void validateUserAge(LocalDate dateOfBirth){
        if(dateOfBirth==null){
            throw getExceptionForKey(PROPERTY_DATE_OF_BIRTH, TYPE_EXCEPTIONS[0]);
        }
        if(Period.between(dateOfBirth,LocalDate.now()).getYears()<MINIMUM_AGE_REQUIRED){
            throw getExceptionForKey(PROPERTY_DATE_OF_BIRTH, TYPE_EXCEPTIONS[1]);
        }
    }
    private static void validateUsingRegex(String text,String regex, String property){
        if(!text.matches(regex)){
            throw getExceptionForKey(property, TYPE_EXCEPTIONS[1]);
        }
    }
    private static void validateIsNotNullOrEmpty(String text, String property) {
        if ( text == null  || text.trim().isEmpty() ) {
            throw getExceptionForKey(property, TYPE_EXCEPTIONS[0]);
        }
    }

    private static RuntimeException getExceptionForKey( String property, String typeException) {
        String key = String.format("%s%s%s",MODEL_USER, property, typeException);
        return EXCEPTION_MAP.get(key).get();
    }


}
