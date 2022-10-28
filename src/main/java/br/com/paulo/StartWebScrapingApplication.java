package br.com.paulo;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.paulo.entity.URLEntity;
import br.com.paulo.service.DownloadFileService;
import br.com.paulo.service.WebScrapingService;

@SpringBootApplication
public class StartWebScrapingApplication {
	
	@Autowired
	WebScrapingService service;
	
	public static void main(String[] args) throws IOException {
		SpringApplication.run(StartWebScrapingApplication.class, args);	
	}
	
	@Bean
	public CommandLineRunner init() {
		return args -> {			
			List<URLEntity> urls = service.scrapHTML();
			urls.forEach(System.out::println);
			DownloadFileService downloadFileService = new DownloadFileService();
			downloadFileService.download(urls);
		};
	}
}
