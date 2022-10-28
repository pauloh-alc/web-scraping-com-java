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
			// URL fornecida no teste1:
			final String URL = "https://www.gov.br/ans/pt-br/assuntos/consumidor/o-que-o-seu-plano-de-saude-deve-cobrir-1/o-que-e-o-rol-de-procedimentos-e-evento-em-saude";
			
			// Lista de URL's para download
			List<URLEntity> urls = service.scrapHTML(URL);
			
			urls.forEach(System.out::println);
			
			DownloadFileService downloadFileService = new DownloadFileService();
			downloadFileService.download(urls);
		};
	}
}
