package com.anatolii.springproject1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Springproject1Application {

	public static void main(String[] args) {
		final SpringApplication app = new SpringApplication(Springproject1Application.class);
		final ConfigurableApplicationContext context = app.run(args);
		context.getBean(TaskDAO.class).getAllByPage(1,10).forEach(System.out::println);
	}

}