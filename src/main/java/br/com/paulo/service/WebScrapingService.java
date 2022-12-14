package br.com.paulo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import br.com.paulo.entity.URLEntity;

@Service
public class WebScrapingService {
	
	private Document connectToThePage(final String URL) throws IOException {
		Document documentHTML = Jsoup.connect(URL).get();
		return documentHTML;
	}
	
	public List<URLEntity> scrapHTML(final String URL) throws IOException {
		Document documentHTML = connectToThePage(URL);
		String id = "parent-fieldname-text";
		
		Element divContainer = documentHTML.getElementById(id);
		
		List<Element> tagsP = new ArrayList<>();
		String class_ = "callout";
		tagsP = divContainer.getElementsByClass(class_);
		
		List<URLEntity> urls = new ArrayList<>();
		
		tagsP.forEach(p -> {
			Element tagA = p.getElementsByTag("a").get(0);
			String href = tagA.attr("href"); 
			
			URLEntity url = new URLEntity();
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
	
	private List<URLEntity> selectSpecificURLFiles(List<URLEntity> urls) {
		return urls.stream().filter(url -> {
			if (url.getName().contains("Anexo")) {
				return true;
			}
			return false;
		}).collect(Collectors.toList());
	}
}
