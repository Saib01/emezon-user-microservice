package com.emazon.user.domain.spi;



public interface IAuthenticationPersistencePort {
   String generateToken(String username,String role);
   String getRole(String inputPassword, String storedHashedPassword);
}
