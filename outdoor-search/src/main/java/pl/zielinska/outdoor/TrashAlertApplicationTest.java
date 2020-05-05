package pl.zielinska.outdoor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"pl.zielinska"})
@EntityScan(basePackages = {"pl.zielinska"})
@ComponentScan(basePackages = {"pl.zielinska"})
public class TrashAlertApplicationTest {

	public static void main(String[] args) {
		SpringApplication.run(TrashAlertApplicationTest.class, args);
	}

}
