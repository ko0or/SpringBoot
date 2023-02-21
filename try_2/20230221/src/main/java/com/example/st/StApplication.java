package com.example.st;

import com.example.st.entity.BoardEntity;
import com.example.st.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing
@SpringBootApplication
public class StApplication {

	public static void main(String[] args) {
		SpringApplication.run(StApplication.class, args);


	}

}
