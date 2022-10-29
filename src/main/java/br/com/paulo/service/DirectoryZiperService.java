package br.com.paulo.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DirectoryZiperService {

	public void zipDirectory(String srcDirectory, String zipFile) throws Exception {
		FileOutputStream fos = new FileOutputStream(zipFile);
		ZipOutputStream zip = new ZipOutputStream(fos);
		String path = "";

		generateDirToZip(path, srcDirectory, zip);

		zip.flush();
		zip.close();
	}
	
	// Helpers methods...............:
	
	private void generateDirToZip(String path, String srcDirectory, ZipOutputStream zip) throws Exception {
		File dir = new File(srcDirectory);

		for (String fileName : dir.list()) {
			if (path.equals(""))
				convertFileToZip(dir.getName(), srcDirectory + "/" + fileName, zip);
			else
				convertFileToZip(path + "/" + dir.getName(), srcDirectory + "/" + fileName, zip);
		}
	}

	private void convertFileToZip(String path, String srcFile, ZipOutputStream zip) throws Exception {
		File dir = new File(srcFile);

		boolean flag = dir.isDirectory();

		if (flag) {
			generateDirToZip(path, srcFile, zip);
		} else {

			byte[] buffer = new byte[1024];
			int len;
			try (FileInputStream in = new FileInputStream(srcFile)) {
				zip.putNextEntry(new ZipEntry(path + "/" + dir.getName()));

				while ((len = in.read(buffer)) > 0) {
					zip.write(buffer, 0, len);
				}
			}
		}
	}
}
