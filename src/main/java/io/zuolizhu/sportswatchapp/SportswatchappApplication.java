package io.zuolizhu.sportswatchapp;

import io.zuolizhu.sportswatchapp.Models.User;
import io.zuolizhu.sportswatchapp.Repositories.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SportswatchappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportswatchappApplication.class, args);
	}
}
