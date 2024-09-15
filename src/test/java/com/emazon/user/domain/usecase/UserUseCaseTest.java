package com.emazon.user.domain.usecase;


import com.emazon.user.domain.exeption.user.*;
import com.emazon.user.domain.model.Role;
import com.emazon.user.domain.model.User;
import com.emazon.user.domain.spi.IRolePersistencePort;
import com.emazon.user.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.emazon.user.utils.TestConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;
    @Mock
    private IRolePersistencePort rolePersistencePort;
    @InjectMocks
    private UserUseCase userUseCase;
    private User user;
    private Role role;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        role = new Role(VALID_ID, VALID_USER_ROLE_AUX, VALID_USER_ROLE_DESCRIPTION);
        user = new User(VALID_USER_NAME, VALID_USER_LAST_NAME, VALID_USER_ID_DOCUMENT, VALID_USER_PHONE_NUMBER, VALID_USER_DATE_OF_BIRTH, VALID_USER_PASSWORD, VALID_USER_EMAIL);
        user.setId(VALID_ID);
        user.setRole(role);
    }

    @Test
    @DisplayName("Should save the user and verify that the persistence port method is called once")
    void saveUser() {
        when(this.rolePersistencePort.findByRoleEnum( VALID_USER_ROLE_AUX)).thenReturn(null);
        when(this.userPersistencePort.isIdDocumentAlreadyInUse(user.getIdDocument())).thenReturn(false);
        when(this.userPersistencePort.isEmailAlreadyInUse(user.getEmail())).thenReturn(false);

        userUseCase.saveUser(user, VALID_USER_ROLE_AUX, VALID_USER_ROLE_DESCRIPTION);

        ArgumentCaptor<Role> roleCaptor = ArgumentCaptor.forClass(Role.class);
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);


        verify(rolePersistencePort, times(1)).saveRole(roleCaptor.capture());

        verify(userPersistencePort, times(1)).saveUser(userCaptor.capture());
        assertEquals(userCaptor.getValue(), user);

    }
    @Test
    @DisplayName("Should save the user and verify that the persistence port method is called once")
    void saveAuxUser() {
        when(this.rolePersistencePort.findByRoleEnum( VALID_USER_ROLE_AUX)).thenReturn(null);
        when(this.userPersistencePort.isIdDocumentAlreadyInUse(user.getIdDocument())).thenReturn(false);
        when(this.userPersistencePort.isEmailAlreadyInUse(user.getEmail())).thenReturn(false);

        userUseCase.saveAuxUser(user);

        ArgumentCaptor<Role> roleCaptor = ArgumentCaptor.forClass(Role.class);
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);


        verify(rolePersistencePort, times(1)).saveRole(roleCaptor.capture());

        verify(userPersistencePort, times(1)).saveUser(userCaptor.capture());
        assertEquals(userCaptor.getValue(), user);

    }
    @Test
    @DisplayName("Should not save user when name is empty or null")
    void shouldNotSaveUserWhenNameIsEmptyOrNull() {
        assertAll(() -> {
            user.setName(EMPTY_PROPERTY);
            assertThrows(UserNameRequiredException.class, () -> userUseCase.saveUser(user,VALID_USER_ROLE_AUX, VALID_USER_ROLE_DESCRIPTION));
        }, () -> {
            user.setName(NULL_PROPERTY);
            assertThrows(UserNameRequiredException.class, () -> userUseCase.saveUser(user,VALID_USER_ROLE_AUX, VALID_USER_ROLE_DESCRIPTION));
        });
    }

    @Test
    @DisplayName("Should not save user when last name is empty or null")
    void shouldNotSaveUserWhenLastNameIsEmptyOrNull() {
        assertAll(() -> {
            user.setLastName(EMPTY_PROPERTY);
            assertThrows(UserLastNameRequiredException.class, () -> userUseCase.saveUser(user,VALID_USER_ROLE_AUX, VALID_USER_ROLE_DESCRIPTION));
        }, () -> {
            user.setLastName(NULL_PROPERTY);
            assertThrows(UserLastNameRequiredException.class, () -> userUseCase.saveUser(user,VALID_USER_ROLE_AUX, VALID_USER_ROLE_DESCRIPTION));
        });
    }

    @Test
    @DisplayName("Should not save user when id document is empty or null")
    void shouldNotSaveUserWhenIdDocumentIsEmptyOrNull() {
        assertAll(() -> {
            user.setIdDocument(EMPTY_PROPERTY);
            assertThrows(UserIdDocumentRequiredException.class, () -> userUseCase.saveUser(user,VALID_USER_ROLE_AUX, VALID_USER_ROLE_DESCRIPTION));
        }, () -> {
            user.setIdDocument(NULL_PROPERTY);
            assertThrows(UserIdDocumentRequiredException.class, () -> userUseCase.saveUser(user,VALID_USER_ROLE_AUX, VALID_USER_ROLE_DESCRIPTION));
        });
    }

    @Test
    @DisplayName("Should not save user when phone number is empty or null")
    void shouldNotSaveUserWhenPhoneNumberIsEmptyOrNull() {
        assertAll(() -> {
            user.setPhoneNumber(EMPTY_PROPERTY);
            assertThrows(UserPhoneNumberRequiredException.class, () -> userUseCase.saveUser(user,VALID_USER_ROLE_AUX, VALID_USER_ROLE_DESCRIPTION));
        }, () -> {
            user.setPhoneNumber(NULL_PROPERTY);
            assertThrows(UserPhoneNumberRequiredException.class, () -> userUseCase.saveUser(user,VALID_USER_ROLE_AUX, VALID_USER_ROLE_DESCRIPTION));
        });
    }

    @Test
    @DisplayName("Should not save user when password is empty or null")
    void shouldNotSaveUserWhenPasswordIsEmptyOrNull() {
        assertAll(() -> {
            user.setPassword(EMPTY_PROPERTY);
            assertThrows(UserPasswordRequiredException.class, () -> userUseCase.saveUser(user,VALID_USER_ROLE_AUX, VALID_USER_ROLE_DESCRIPTION));
        }, () -> {
            user.setPassword(NULL_PROPERTY);
            assertThrows(UserPasswordRequiredException.class, () -> userUseCase.saveUser(user,VALID_USER_ROLE_AUX, VALID_USER_ROLE_DESCRIPTION));
        });
    }

    @Test
    @DisplayName("Should not save user when email is empty or null")
    void shouldNotSaveUserWhenEmailIsEmptyOrNull() {
        assertAll(() -> {
            user.setEmail(EMPTY_PROPERTY);
            assertThrows(UserEmailRequiredException.class, () -> userUseCase.saveUser(user,VALID_USER_ROLE_AUX, VALID_USER_ROLE_DESCRIPTION));
        }, () -> {
            user.setEmail(NULL_PROPERTY);
            assertThrows(UserEmailRequiredException.class, () -> userUseCase.saveUser(user,VALID_USER_ROLE_AUX, VALID_USER_ROLE_DESCRIPTION));
        });
    }

    @Test
    @DisplayName("Should not save user when email is empty or null")
    void shouldNotSaveUserWhenDateOfBirthIsEmptyOrNull() {
        when(this.rolePersistencePort.findByRoleEnum(role.getRoleEnum())).thenReturn(role);
        when(this.userPersistencePort.isIdDocumentAlreadyInUse(user.getIdDocument())).thenReturn(false);
        when(this.userPersistencePort.isEmailAlreadyInUse(user.getEmail())).thenReturn(false);

        user.setDateOfBirth(null);

        assertThrows(UserDateOfBirthRequiredException.class, () -> userUseCase.saveUser(user,VALID_USER_ROLE_AUX, VALID_USER_ROLE_DESCRIPTION));
    }

    @Test
    @DisplayName("Name validation should fail when the name doesn't match the regex pattern.")
    void shouldNotMatchInvalidNamePattern() {
        user.setName(INVALID_USER_NAME);
        assertThrows(UserNameInvalidException.class, () -> userUseCase.saveUser(user,VALID_USER_ROLE_AUX, VALID_USER_ROLE_DESCRIPTION));
    }

    @Test
    @DisplayName("Last name validation should fail when the last name doesn't match the regex pattern.")
    void shouldNotMatchInvalidLastNamePattern() {
        user.setLastName(INVALID_USER_LAST_NAME);
        assertThrows(UserLastNameInvalidException.class, () -> userUseCase.saveUser(user,VALID_USER_ROLE_AUX, VALID_USER_ROLE_DESCRIPTION));
    }

    @Test
    @DisplayName("Id document validation should fail when the id document doesn't match the regex pattern.")
    void shouldNotMatchInvalidIdDocumentPattern() {
        user.setIdDocument(INVALID_USER_ID_DOCUMENT);
        assertThrows(UserIdDocumentInvalidException.class, () -> userUseCase.saveUser(user,VALID_USER_ROLE_AUX, VALID_USER_ROLE_DESCRIPTION));
    }

    @Test
    @DisplayName("Phone number validation should fail when the phone number doesn't match the regex pattern.")
    void shouldNotMatchInvalidPhoneNumberPattern() {
        user.setPhoneNumber(INVALID_USER_PHONE_NUMBER);
        assertThrows(UserPhoneNumberInvalidException.class, () -> userUseCase.saveUser(user,VALID_USER_ROLE_AUX, VALID_USER_ROLE_DESCRIPTION));
    }

    @Test
    @DisplayName("Password validation should fail when the password doesn't match the regex pattern.")
    void shouldNotMatchInvalidPasswordPattern() {
        user.setPassword(INVALID_USER_PASSWORD);
        assertThrows(UserPasswordInvalidException.class, () -> userUseCase.saveUser(user,VALID_USER_ROLE_AUX, VALID_USER_ROLE_DESCRIPTION));
    }

    @Test
    @DisplayName("Email validation should fail when the email doesn't match the regex pattern.")
    void shouldNotMatchInvalidEmailPattern() {
        user.setEmail(INVALID_USER_EMAIL);
        assertThrows(UserEmailInvalidException.class, () -> userUseCase.saveUser(user,VALID_USER_ROLE_AUX, VALID_USER_ROLE_DESCRIPTION));
    }


    @Test
    @DisplayName("Should not save user when Id Document is already in use")
    void shouldNotSaveUserWhenIdDocumentIsAlreadyInUse() {
        when(this.rolePersistencePort.findByRoleEnum(role.getRoleEnum())).thenReturn(role);
        when(this.userPersistencePort.isIdDocumentAlreadyInUse(user.getIdDocument())).thenReturn(true);
        assertThrows(UserIdDocumentAlreadyExistException.class, () -> userUseCase.saveUser(user,VALID_USER_ROLE_AUX, VALID_USER_ROLE_DESCRIPTION));
    }

    @Test
    @DisplayName("Should not save user when email is already in use")
    void shouldNotSaveUserWhenEmailIsAlreadyInUse() {
        when(this.rolePersistencePort.findByRoleEnum(role.getRoleEnum())).thenReturn(role);
        when(this.userPersistencePort.isIdDocumentAlreadyInUse(user.getIdDocument())).thenReturn(false);
        when(this.userPersistencePort.isEmailAlreadyInUse(user.getEmail())).thenReturn(true);

        assertThrows(UserEmailAlreadyExistException.class, () -> userUseCase.saveUser(user,VALID_USER_ROLE_AUX, VALID_USER_ROLE_DESCRIPTION));
    }

    @Test
    @DisplayName("Should not save user when date of birth is invalid")
    void shouldNotSaveUserWhenDateOfBirthIsInvalid() {
        when(this.rolePersistencePort.findByRoleEnum(role.getRoleEnum())).thenReturn(role);
        when(this.userPersistencePort.isIdDocumentAlreadyInUse(user.getIdDocument())).thenReturn(false);
        when(this.userPersistencePort.isEmailAlreadyInUse(user.getEmail())).thenReturn(false);

        user.setDateOfBirth(INVALID_USER_DATE_OF_BIRTH);

        assertThrows(UserDateOfBirthInvalidException.class, () -> userUseCase.saveUser(user,VALID_USER_ROLE_AUX, VALID_USER_ROLE_DESCRIPTION));
    }
}