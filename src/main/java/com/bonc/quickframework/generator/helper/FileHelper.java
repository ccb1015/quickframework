package com.bonc.quickframework.generator.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileHelper {

	public static void main(String[] args) throws IOException {
		// System.err.println(getExtension("c:/a.JAR"));
		File file = new File("aC");
		System.err.println(file.getAbsoluteFile().getName());

	}

	public static List ignoreList;
	public static Set copyFileList;

	/**
	 * 获取相对路径
	 * 
	 * @param baseDir
	 * @param file
	 */
	public static String getRelativePath(File baseDir, File file) {
		if (baseDir.equals(file))
			return "";
		if (baseDir.getParentFile() == null)
			return file.getAbsolutePath().substring(
					baseDir.getAbsolutePath().length());
		return file.getAbsolutePath().substring(
				baseDir.getAbsolutePath().length() + 1);
	}

	/**
	 * 列出所有文件(不包含忽略文件)
	 * 
	 * @param dir
	 * @return
	 * @throws IOException
	 */
	public static List searchAllNotIgnoreFile(File dir) throws IOException {
		ArrayList arrayList = new ArrayList();
		searchAllNotIgnoreFile(dir, arrayList);
		Collections.sort(arrayList, new Comparator() {
			public int compare(File o1, File o2) {
				return o1.getAbsolutePath().compareTo(o2.getAbsolutePath());
			}

			public int compare(Object o1, Object o2) {
				return ((File) o1).getAbsolutePath().compareTo(
						((File) o2).getAbsolutePath());
			}

		});
		return arrayList;
	}

	public static void searchAllNotIgnoreFile(File dir, List collector)
			throws IOException {
		collector.add(dir);
		if ((!(dir.isHidden())) && (dir.isDirectory())
				&& (!(isIgnoreFile(dir)))) {
			File[] subFiles = dir.listFiles();
			for (int i = 0; i < subFiles.length; ++i)
				searchAllNotIgnoreFile(subFiles[i], collector);
		}
	}

	public static InputStream getInputStream(String file)
			throws FileNotFoundException {
		InputStream inputStream = null;
		if (file.startsWith("classpath:"))
			inputStream = FileHelper.class.getClassLoader()
					.getResourceAsStream(file.substring("classpath:".length()));
		else
			inputStream = new FileInputStream(file);

		return inputStream;
	}

	public static File mkdir(String dir, String file) {
		if (dir == null)
			throw new IllegalArgumentException("dir must be not null");
		File result = new File(dir, file);
		parnetMkdir(result);
		return result;
	}

	public static void parnetMkdir(File outputFile) {
		if (outputFile.getParentFile() != null)
			outputFile.getParentFile().mkdirs();
	}

	/**
	 * 是否为忽略文件
	 * 
	 * @param file
	 * @return
	 */
	public static boolean isIgnoreFile(File file) {
		for (int i = 0; i < ignoreList.size(); ++i)
			if (file.getName().equals(ignoreList.get(i)))
				return true;

		return false;
	}

	/**
	 * 是否为copy文件
	 * 
	 * @param file
	 * @return
	 */
	public static boolean isCopyFile(File file) {
		if (file.isDirectory())
			return false;
		return isCopyFile(file.getName());
	}

	public static boolean isCopyFile(String filename) {
		String str = getExtension(filename);
		if (((str == null) || (str.trim().length() == 0))) {
			return false;
		}
		return copyFileList.contains(getExtension(filename).toLowerCase());
	}

	/**
	 * 获取文件后缀
	 * 
	 * @param filename
	 * @return
	 */
	public static String getExtension(String filename) {

		if (filename == null)
			return null;
		String name = new File(filename).getName();
		int index = name.lastIndexOf(".");
		if (index == -1)
			return "";

		return name.substring(index + 1);
	}

	public static void deleteDirectory(File directory) throws IOException {
		if (!(directory.exists())) {
			return;
		}

		cleanDirectory(directory);
		if (!(directory.delete())) {
			String message = "Unable to delete directory " + directory + ".";

			throw new IOException(message);
		}
	}

	public static boolean deleteQuietly(File file) {
		if (file == null)
			return false;
		try {
			if (file.isDirectory())
				cleanDirectory(file);
		} catch (Exception e) {
		}
		try {
			return file.delete();
		} catch (Exception e) {
		}
		return false;
	}

	public static void cleanDirectory(File directory) throws IOException {
		String message;
		if (!(directory.exists())) {
			message = directory + " does not exist";
			throw new IllegalArgumentException(message);
		}

		if (!(directory.isDirectory())) {
			message = directory + " is not a directory";
			throw new IllegalArgumentException(message);
		}

		File[] files = directory.listFiles();
		if (files == null) {
			throw new IOException("Failed to list contents of " + directory);
		}

		IOException exception = null;
		for (int i = 0; i < files.length; ++i) {
			File file = files[i];
			try {
				forceDelete(file);
			} catch (IOException ioe) {
				exception = ioe;
			}
		}

		if (null != exception)
			throw exception;
	}

	public static void forceDelete(File file) throws IOException {
		if (file.isDirectory()) {
			deleteDirectory(file);
		} else {
			boolean filePresent = file.exists();
			if (!(file.delete())) {
				if (!(filePresent))
					throw new FileNotFoundException("File does not exist: "
							+ file);

				String message = "Unable to delete file: " + file;

				throw new IOException(message);
			}
		}
	}

	public static boolean isApiFile(String fileName) {
		if (fileName == null)
			return false;
		File file = new File(fileName);
		if (file.isDirectory())
			return false;
		String name = file.getName();
		String parentName = file.getAbsoluteFile().getParentFile().getName();
		String parentParentName = file.getAbsoluteFile().getParentFile()
				.getParentFile().getName();

		return name.toLowerCase().endsWith("cxf.xml")
				|| parentName.equalsIgnoreCase("api")
				|| parentParentName.equalsIgnoreCase("api");
	}

	static {
		ignoreList = new ArrayList();
		ignoreList.add(".svn");
		ignoreList.add("CVS");
		ignoreList.add(".cvsignore");
		ignoreList.add(".copyarea.db");
		ignoreList.add("SCCS");
		ignoreList.add("vssver.scc");
		ignoreList.add(".DS_Store");
		ignoreList.add(".git");
		ignoreList.add(".gitignore");

		copyFileList = new HashSet();
		copyFileList.add("jar");
//		copyFileList.add("js");
		copyFileList.add("css");
		copyFileList.add("png");
		copyFileList.add("jpg");
		copyFileList.add("gif");
		
		//字体
		copyFileList.add("eot");
		copyFileList.add("svg");
		copyFileList.add("ttf");
		copyFileList.add("woff");
		copyFileList.add("woff2");

		copyFileList.add(".tld");

		// todo:load from property

	}
}
