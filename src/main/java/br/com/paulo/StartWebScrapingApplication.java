package br.com.paulo;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.paulo.service.WebScrapingService;

@SpringBootApplication
public class StartWebScrapingApplication {
	
	public static  void main(String[] args) throws IOException {
		SpringApplication.run(StartWebScrapingApplication.class, args);	
		
		WebScrapingService service = new WebScrapingService();
		service.scrapHTML();
	}

}
