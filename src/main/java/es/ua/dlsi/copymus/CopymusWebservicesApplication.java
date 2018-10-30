package es.ua.dlsi.copymus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class CopymusWebservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CopymusWebservicesApplication.class, args);
	}
}
