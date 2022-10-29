package br.com.paulo.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.FileUtils;

import br.com.paulo.entity.URLEntity;

public class DownloadFileService {
	
	public void download(List<URLEntity> urls) throws Exception {
		
		urls.forEach(elementURL -> {
			URL url;
			try {
				
				url = new URL(elementURL.getHref());
				File file = new File("/tmp/dir/" + elementURL.getName());
				FileUtils.copyURLToFile(url, file);
				
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		});
		
	}
}
