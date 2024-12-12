package rs.raf.student.fos;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import rs.raf.student.fos.logger.Logger;

@EnableJpaAuditing
@SpringBootApplication
public class FOSApplication {

	public static void main(String[] args) {
		Logger.setLogger(LoggerFactory.getLogger(FOSApplication.class));

		SpringApplication.run(FOSApplication.class, args);
	}

}
