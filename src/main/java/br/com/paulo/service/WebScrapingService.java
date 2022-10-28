package br.com.paulo.service;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebScrapingService {
	
	final static String URL = "https://www.gov.br/ans/pt-br/assuntos/consumidor/o-que-o-seu-plano-de-saude-deve-cobrir-1/o-que-e-o-rol-de-procedimentos-e-evento-em-saude";
	
	public Document connectToThePage() throws IOException {
		Document documentHTML = Jsoup.connect(URL).get();
		return documentHTML;
	}

}
