package br.com.paulo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import br.com.paulo.entity.URL;

public class WebScrapingService {
	
	final static String URL = "https://www.gov.br/ans/pt-br/assuntos/consumidor/o-que-o-seu-plano-de-saude-deve-cobrir-1/o-que-e-o-rol-de-procedimentos-e-evento-em-saude";
	
	private Document connectToThePage() throws IOException {
		Document documentHTML = Jsoup.connect(URL).get();
		return documentHTML;
	}
	
	public List<URL> scrapHTML() throws IOException {
		Document documentHTML = connectToThePage();
		String id = "parent-fieldname-text";
		
		Element divContainer = documentHTML.getElementById(id);
		
		List<Element> tagsP = new ArrayList<>();
		String class_ = "callout";
		tagsP = divContainer.getElementsByClass(class_);
		
		List<URL> urls = new ArrayList<>();
		
		tagsP.forEach(p -> {
			Element tagA = p.getElementsByTag("a").get(0);
			String href = tagA.attr("href"); 
			
			URL url = new URL();
			url.setName(getFileName(href));
			url.setHref(href);
			
			urls.add(url);
		});
		
		return selectSpecificURLFiles(urls);
	}

	private String getFileName(String href) {
		String[] slices = href.split("/");
		return slices[slices.length-1];
	}
	
	private List<URL> selectSpecificURLFiles(List<URL> urls) {
		return urls.stream().filter(url -> {
			if (url.getName().contains("Anexo")) {
				return true;
			}
			return false;
		}).collect(Collectors.toList());
	}
}
