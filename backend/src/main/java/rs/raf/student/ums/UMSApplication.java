package rs.raf.student.ums;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import rs.raf.student.ums.logger.Logger;

@EnableJpaAuditing
@SpringBootApplication
public class UMSApplication {

	public static void main(String[] args) {
		Logger.setLogger(LoggerFactory.getLogger(UMSApplication.class));

		SpringApplication.run(UMSApplication.class, args);
	}

}
