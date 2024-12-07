package rs.raf.student.ums;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class UMSApplication {

	public static void main(String[] args) {
		SpringApplication.run(UMSApplication.class, args);
	}

}
