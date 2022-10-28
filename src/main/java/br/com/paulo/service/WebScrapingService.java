package br.com.paulo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WebScrapingService {
	
	final static String URL = "https://www.gov.br/ans/pt-br/assuntos/consumidor/o-que-o-seu-plano-de-saude-deve-cobrir-1/o-que-e-o-rol-de-procedimentos-e-evento-em-saude";
	
	private Document connectToThePage() throws IOException {
		Document documentHTML = Jsoup.connect(URL).get();
		return documentHTML;
	}
	
	public void scrapHTML() throws IOException {
		Document documentHTML = connectToThePage();
		String id = "parent-fieldname-text";
		
		// <div> contem tags <p> que por sua vez possuem os atributos href's, contendo os links para os PDF's
		
		Element divContainer = documentHTML.getElementById(id);
		
		List<Element> tagsP = new ArrayList<>();
		String class_ = "callout";
		tagsP = divContainer.getElementsByClass(class_);
		
		System.out.println(tagsP);
	}

}
