package com.example.SaleProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"Config","Controller","DTO", "Exceptions","Filter","Model","Repository","Services","Util"}) // Scan để quét các bean thông thường như @component
                                                                                    // @controller, @services,@Config,...
@EnableJpaRepositories(basePackages = "Repository") // Scan để quét các interface JPA như repository
@EntityScan(basePackages = "Model") // Scan để quét các lớp Entity
public class SaleProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaleProjectApplication.class, args);
	}

}
