package com.shadow.javaUtils;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ReadFiles {
	

	public static Properties loadPropertyFile(String fileName) {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			prop.load(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;	
	}

	public static ArrayList<String> readTxtFile(String fileName){
		ArrayList<String> list = new ArrayList<>();

		try(Stream<String> stream = Files.lines(Paths.get(fileName))){
			list = (ArrayList<String>) stream.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		//list.forEach(System.out::println);
		return list;
	}
}
