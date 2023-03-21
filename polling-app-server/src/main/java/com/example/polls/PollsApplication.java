package com.example.polls;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@SpringBootApplication
@EntityScan(basePackageClasses = {
		PollsApplication.class,
		Jsr310JpaConverters.class
})
@EnableFeignClients
public class PollsApplication {

	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		SpringApplication.run(PollsApplication.class, args);


		List<String> thelistwithseveral_words=new ArrayList<>();
		thelistwithseveral_words.add("apepep");
		thelistwithseveral_words.add("apepep");
		thelistwithseveral_words.add("apepep");
		thelistwithseveral_words.add("bpepep");

		List<String> result= (List<String>) thelistwithseveral_words.stream().filter(x-> x.contains("a") && x.length()==3);
		result.forEach(System.out::println);

	}
}
