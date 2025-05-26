package pl.zenit.infobazademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class InfobazademoApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfobazademoApplication.class, args);
	}

}
