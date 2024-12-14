package com.cdac;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cdac.model.User;
import com.cdac.repository.UserRepository;

@SpringBootApplication
public class OnlineCertificateManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineCertificateManagementSystemApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
	    return args -> {
	        // Check if an admin user already exists
	        User existingAdmin = userRepository.findByUsername("admin");
	        if (existingAdmin == null) {
	            User newAdmin = new User();
	            newAdmin.setUsername("admin");
	            // Encrypt password before saving
	            newAdmin.setPassword(passwordEncoder.encode("Alok@123"));
	            newAdmin.setRole("ADMIN");
	            userRepository.save(newAdmin);
	            System.out.println("Admin user 'alok' created successfully with password 'Alok@123'");
	        }
	    };
	}


}