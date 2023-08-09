package com.zakaria.ecom;

import com.zakaria.ecom.config.RsaKeysConfig;
import com.zakaria.ecom.services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeysConfig.class)
public class EcomApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomApplication.class, args);
	}
	@Bean
	PasswordEncoder passwordEncoder () {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner commandLineRunnerUserDetails(AccountService appAcocountService) {
		return args -> {

			// method personalize
			appAcocountService.addNewRole("USER");
			appAcocountService.addNewRole("ADMIN");

			appAcocountService.addNewUser("admin", "1234", "admin@gmail.com", "1234");


			appAcocountService.attachRoleToUser("admin", "USER");
			appAcocountService.attachRoleToUser("admin", "ADMIN");

		};
	}
}
