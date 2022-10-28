package br.com.paulo;

import java.io.IOException;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.paulo.entity.URL;
import br.com.paulo.service.WebScrapingService;

@SpringBootApplication
public class StartWebScrapingApplication {
	
	public static  void main(String[] args) throws IOException {
		SpringApplication.run(StartWebScrapingApplication.class, args);	
		
		WebScrapingService service = new WebScrapingService();
		List<URL> urls = service.scrapHTML();
		urls.forEach(System.out::println);
	}

}
