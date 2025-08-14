package com.sparklecow.pandawok;

import com.sparklecow.pandawok.user.entity.Role;
import com.sparklecow.pandawok.user.model.RoleEnum;
import com.sparklecow.pandawok.user.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
@RequiredArgsConstructor
@Slf4j
public class PandawokApplication {

	private final RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(PandawokApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
		return args -> {
			if (roleRepository.count() == 0) {
				List<Role> roles = Arrays.stream(RoleEnum.values())
						.map(roleEnum -> {
							Role role = new Role();
							role.setRoleEnum(roleEnum);
							return role;
						})
						.toList();
				roleRepository.saveAll(roles);
				log.info("Roles created");
			}
		};
	}
}
