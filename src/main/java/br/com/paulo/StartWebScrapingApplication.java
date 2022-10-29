package br.com.paulo;

import java.io.IOException;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.paulo.entity.URLEntity;
import br.com.paulo.service.DirectoryZiper;
import br.com.paulo.service.DownloadFileService;
import br.com.paulo.service.WebScrapingService;

@SpringBootApplication
public class StartWebScrapingApplication {
	
	
	
	public static void main(String[] args) throws IOException {
		SpringApplication.run(StartWebScrapingApplication.class, args);	
	}
	
	@Bean
	public CommandLineRunner init() {
		return args -> {
			// 1. URL do GOV BR fornecida no teste1.
			final String URL = "https://www.gov.br/ans/pt-br/assuntos/consumidor/o-que-o-seu-plano-de-saude-deve-cobrir-1/o-que-e-o-rol-de-procedimentos-e-evento-em-saude";
			
			WebScrapingService service = new WebScrapingService();
			
			// 2. Lista de URL'S que acessam os arquivos que foram escolhidos para serem baixados.
			List<URLEntity> urls = service.scrapHTML(URL);
			
			DownloadFileService downloadFileService = new DownloadFileService();
			
			// 3. Realizando downloads dos arquivos pedidos por meio das URL's "raspadas" na Web
			downloadFileService.download(urls);
			
			DirectoryZiper folderZiper = new DirectoryZiper();
			
			// Diretorio com os arquivos baixados............................................
			String dir = "/tmp/dir";
			// Nome do arquivo que vai ser gerado, contendo todos os arquivos baixados.......
			String zipFile = "/tmp/zipado.zip";
			
			// 4. Zipando. 
			folderZiper.zipDirectory(dir, zipFile);
			
			System.out.println("Os arquivos foram solicitados e zipados com sucesso!");
		};
	}
}
