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

import static com.emazon.user.domain.exeption.ExceptionResponseDomain.*;
import static com.emazon.user.domain.utils.DomainConstants.*;
import static java.lang.String.format;

public class UserValidator {

    private static final String[] TYPE_EXCEPTIONS = {"Required", "Invalid"};

    private static final String REGEX_PHONE_NUMBER = "^\\+?\\d{1,12}$";
    private static final String REGEX_ID_DOCUMENT = "^\\d+$";
    private static final String REGEX_EMAIL = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String REGEX_PASSWORD = "^(?!.*password)(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&_])[A-Za-z\\d@$!%*?&_]{8,}$";
    private static final String REGEX_NAME_OR_LAST_NAME = "^[A-Za-z\\s]{1,50}$";
    private static final Map<String, Supplier<RuntimeException>> EXCEPTION_MAP = new HashMap<>();

    static {
        EXCEPTION_MAP.put(format(KEY_TEMPLATE,PROPERTY_NAME,TYPE_EXCEPTIONS[0]), () -> new UserNameRequiredException(USER_NAME_REQUIRED.getMessage()));
        EXCEPTION_MAP.put(format(KEY_TEMPLATE,PROPERTY_LAST_NAME,TYPE_EXCEPTIONS[0]), () -> new UserLastNameRequiredException(USER_LAST_NAME_REQUIRED.getMessage()));
        EXCEPTION_MAP.put(format(KEY_TEMPLATE,PROPERTY_ID_DOCUMENT,TYPE_EXCEPTIONS[0]), () -> new UserIdDocumentRequiredException(USER_ID_DOCUMENT_REQUIRED.getMessage()));
        EXCEPTION_MAP.put(format(KEY_TEMPLATE,PROPERTY_PHONE_NUMBER,TYPE_EXCEPTIONS[0]), () -> new UserPhoneNumberRequiredException(USER_PHONE_NUMBER_REQUIRED.getMessage()));
        EXCEPTION_MAP.put(format(KEY_TEMPLATE,PROPERTY_DATE_OF_BIRTH,TYPE_EXCEPTIONS[0]), () -> new UserDateOfBirthRequiredException(USER_DATE_OF_BIRTH_REQUIRED.getMessage()));
        EXCEPTION_MAP.put(format(KEY_TEMPLATE,PROPERTY_PASSWORD,TYPE_EXCEPTIONS[0]), () -> new UserPasswordRequiredException(USER_PASSWORD_REQUIRED.getMessage()));
        EXCEPTION_MAP.put(format(KEY_TEMPLATE,PROPERTY_EMAIL,TYPE_EXCEPTIONS[0]), () -> new UserEmailRequiredException(USER_EMAIL_REQUIRED.getMessage()));


        EXCEPTION_MAP.put(format(KEY_TEMPLATE,PROPERTY_NAME,TYPE_EXCEPTIONS[1]), () -> new UserNameInvalidException(USER_NAME_INVALID.getMessage()));
        EXCEPTION_MAP.put(format(KEY_TEMPLATE,PROPERTY_LAST_NAME,TYPE_EXCEPTIONS[1]), () -> new UserLastNameInvalidException(USER_LAST_NAME_INVALID.getMessage()));
        EXCEPTION_MAP.put(format(KEY_TEMPLATE,PROPERTY_ID_DOCUMENT,TYPE_EXCEPTIONS[1]), () -> new UserIdDocumentInvalidException(USER_ID_DOCUMENT_INVALID.getMessage()));
        EXCEPTION_MAP.put(format(KEY_TEMPLATE,PROPERTY_PHONE_NUMBER,TYPE_EXCEPTIONS[1]), () -> new UserPhoneNumberInvalidException(USER_PHONE_NUMBER_INVALID.getMessage()));
        EXCEPTION_MAP.put(format(KEY_TEMPLATE,PROPERTY_DATE_OF_BIRTH,TYPE_EXCEPTIONS[1]), () -> new UserDateOfBirthInvalidException(USER_DATE_OF_BIRTH_INVALID.getMessage()));
        EXCEPTION_MAP.put(format(KEY_TEMPLATE,PROPERTY_PASSWORD,TYPE_EXCEPTIONS[1]), () -> new UserPasswordInvalidException(USER_PASSWORD_INVALID.getMessage()));
        EXCEPTION_MAP.put(format(KEY_TEMPLATE,PROPERTY_EMAIL,TYPE_EXCEPTIONS[1]), () -> new UserEmailInvalidException(USER_EMAIL_INVALID.getMessage()));
    }

    private UserValidator() {
    }

    public static void validateProperties(User user, IRolePersistencePort rolePersistencePort) {
        validateNotNullNotEmptyAndMatchesRegex(user.getName(), REGEX_NAME_OR_LAST_NAME, PROPERTY_NAME);
        validateNotNullNotEmptyAndMatchesRegex(user.getLastName(), REGEX_NAME_OR_LAST_NAME, PROPERTY_LAST_NAME);
        validateNotNullNotEmptyAndMatchesRegex(user.getIdDocument(), REGEX_ID_DOCUMENT, PROPERTY_ID_DOCUMENT);
        validateNotNullNotEmptyAndMatchesRegex(user.getPhoneNumber(), REGEX_PHONE_NUMBER, PROPERTY_PHONE_NUMBER);
        validateNotNullNotEmptyAndMatchesRegex(user.getPassword(), REGEX_PASSWORD, PROPERTY_PASSWORD);
        validateNotNullNotEmptyAndMatchesRegex(user.getEmail(), REGEX_EMAIL, PROPERTY_EMAIL);
        validateUserAge(user.getDateOfBirth());
        getIdRole(user.getRole(), rolePersistencePort);
    }

    private static void getIdRole(Role role, IRolePersistencePort rolePersistencePort) {
        Role existRole = rolePersistencePort.findByRoleEnum(role.getRoleEnum());
        role.setId(
                existRole == null ? rolePersistencePort.saveRole(role) : existRole.getId()
        );
    }

    public static void validateEmailIsAlreadyInUse(User user, IUserPersistencePort userPersistencePort) {
        if (userPersistencePort.isEmailAlreadyInUse(user.getEmail())) {
            throw new UserEmailAlreadyExistException(USER_EMAIL_ALREADY_EXISTS.getMessage());
        }
    }

    public static void validateIdDocumentIsAlreadyInUse(User user, IUserPersistencePort userPersistencePort) {
        if (userPersistencePort.isIdDocumentAlreadyInUse(user.getIdDocument())) {
            throw new UserIdDocumentAlreadyExistException(USER_ID_DOCUMENT_ALREADY_EXISTS.getMessage());
        }
    }

    private static void validateUserAge(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            throw getExceptionForKey(PROPERTY_DATE_OF_BIRTH, TYPE_EXCEPTIONS[0]);
        }
        if (Period.between(dateOfBirth, LocalDate.now()).getYears() < MINIMUM_AGE_REQUIRED) {
            throw getExceptionForKey(PROPERTY_DATE_OF_BIRTH, TYPE_EXCEPTIONS[1]);
        }
    }

    private static void validateNotNullNotEmptyAndMatchesRegex(String text, String regex, String property) {
        validateIsNotNullOrEmpty(text, property);
        validateUsingRegex(text, regex, property);
    }

    private static void validateUsingRegex(String text, String regex, String property) {
        if (!text.matches(regex)) {
            throw getExceptionForKey(property, TYPE_EXCEPTIONS[1]);
        }
    }

    private static void validateIsNotNullOrEmpty(String text, String property) {
        if (text == null || text.trim().isEmpty()) {
            throw getExceptionForKey(property, TYPE_EXCEPTIONS[0]);
        }
    }

    private static RuntimeException getExceptionForKey(String property, String typeException) {
        String key = format("%s%s%s", MODEL_USER, property, typeException);
        return EXCEPTION_MAP.get(key).get();
    }


}
