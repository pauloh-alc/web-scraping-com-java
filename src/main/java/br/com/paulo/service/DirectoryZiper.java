package br.com.paulo.service;

import java.io.FileOutputStream;
import java.util.zip.ZipOutputStream;

public class DirectoryZiper {
	
	public void zipDirectory(String srcDirectory, String zipFile) throws Exception {
		FileOutputStream fos = new FileOutputStream(zipFile);
		ZipOutputStream zip = new ZipOutputStream(fos);
		String path = "";
		
		generateDirToZip(path, srcDirectory, zip);
		
		zip.flush();
		zip.close();
	}

	private void generateDirToZip(String path, String srcDirectory, ZipOutputStream zip) throws Exception {
		// TODO Auto-generated method stub
	}
}
