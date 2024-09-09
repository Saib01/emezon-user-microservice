package com.emazon.user;

import com.emazon.user.domain.usecase.UserUseCase;
import com.emazon.user.domain.utils.RoleEnum;
import com.emazon.user.infraestructure.output.jpa.entity.RoleEntity;
import com.emazon.user.infraestructure.output.jpa.entity.UserEntity;
import com.emazon.user.infraestructure.output.jpa.mapper.UserEntityMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserUseCase userUseCase, UserEntityMapper userEntityMapper) {
		return args -> {
			UserEntity admin = UserEntity.builder()
					.id(null)
					.name("Johan")
					.lastName("Santiago")
					.email("mail@mail.com")
					.roleEntity(new RoleEntity(null, RoleEnum.ADMIN,"Dios del Sistema"))
					.phoneNumber("+573116322584")
					.dateOfBirth(LocalDate.parse("1990-08-11"))
					.idDocument("123123123123")
					.password("icelaCreyoDem@siado3123")
					.build();

			userUseCase.saveUser(
					userEntityMapper.toUser(admin)
			);
		};

	}

}
